package com.sparta.plannerHomework.repository;

import com.sparta.plannerHomework.dto.PlannerRequestDto;
import com.sparta.plannerHomework.dto.PlannerResponseDto;
import com.sparta.plannerHomework.entity.Planner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

/*public interface PlannerRepository extends JpaRepository<Planner, Long> {
    List<Planner> findAllByOrderByModifiedAtDesc();
    List<Planner> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);

    void delete(Planner planner);
}*/

@Component
public class PlannerRepository{
    private final JdbcTemplate jdbcTemplate;
    public PlannerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Planner> findAllByOrderByModifiedAtDesc() {
        return null;
    }

    public List<Planner> findAllByContentsContainsOrderByModifiedAtDesc(String keyword) {
        return null;
    }

    //DB 저장 저장한 메모 다시 리턴
    public Planner save(Planner planner) {
        // DB 저장 레포지가 해야겠쥬?
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        //? ?인이유 = insert문 결과는 매번 바뀔거아님 동적으로
        //1번 물음표 = 밑에 setString1번
        //2번 물음표 = 밑에 setString 2번
        String sql = "INSERT INTO planner (title,contents,username, password,date) VALUES (?, ?, ?,?,?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    //?1번
                    preparedStatement.setString(1, planner.getTitle());
                    //?2번
                    preparedStatement.setString(2, planner.getContents());
                    preparedStatement.setString(3, planner.getUsername());
                    preparedStatement.setString(4, planner.getPassword());
                    preparedStatement.setString(5, planner.getDate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        //Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        Long id = keyHolder.getKey().longValue();

        planner.setId(id);
        return planner;
    }

    public List<PlannerResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM planner";

        //
        return jdbcTemplate.query(sql, new RowMapper<PlannerResponseDto>() {
            //값을 여러번 받아올거임 포문돌듯이 그럼 이 메소드 여러번 실행되는거임
            //받아온 데이터들이 ResultSet us
            @Override
            public PlannerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                //get() 해당하는자료형, 컬럼이름 써서 값 가져오기
                // column 하나가 자바 객체 하나가 되는거임!!
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String date = rs.getString("date");
                return new PlannerResponseDto(id, title, contents, username, password,date);
            }
        });
    }



    public void update(Long id, PlannerRequestDto requestDto) {
        String sql = "UPDATE planner SET title=?,contents = ?,username = ?, password = ?,date = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(),requestDto.getContents(),requestDto.getUsername(), requestDto.getPassword(),requestDto.getDate(), id);
    }

    public void delelte(Long id) {
        // 일정 삭제
        String sql = "DELETE FROM planner WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Planner findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM planner WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Planner planner = new Planner();
                planner.setTitle(resultSet.getString("title"));
                planner.setContents(resultSet.getString("contents"));
                planner.setUsername(resultSet.getString("username"));
                planner.setPassword(resultSet.getString("password"));
                planner.setDate(resultSet.getString("date"));
                return planner;
            } else {
                return null;
            }
        }, id);
    }
}

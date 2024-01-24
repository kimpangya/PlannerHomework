package com.sparta.plannerHomework.service;

import com.sparta.plannerHomework.dto.PlannerRequestDto;
import com.sparta.plannerHomework.dto.PlannerResponseDto;

import com.sparta.plannerHomework.entity.Planner;

import com.sparta.plannerHomework.repository.PlannerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;

    public PlannerService(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public PlannerResponseDto createPlanner(PlannerRequestDto requestDto) {
        // RequestDto -> Entity
        Planner planner = new Planner(requestDto);

        // DB 저장
        Planner savePlanner = plannerRepository.save(planner);

        // Entity -> ResponseDto
        return new PlannerResponseDto(savePlanner);
    }

    public List<PlannerResponseDto> getPlanners() {
        // DB 조회
        return plannerRepository.findAllByOrderByModifiedAtDesc()
                .stream()
                .map(PlannerResponseDto::new)
                .toList();
    }

    public List<PlannerResponseDto> getPlannersByKeyword(String keyword) {
        return plannerRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(PlannerResponseDto::new).toList();
    }

    @Transactional
    public PlannerResponseDto updatePlanner(Long id, PlannerRequestDto requestDto) {
        // 해당 플랜이 DB에 존재하는지 확인
        Planner planner= findPlanner(id);
        //비밀번호 일치할 때만
        if(requestDto.getPassword().equals(planner.getPassword())){
            // 플랜 내용 수정
            planner.update(requestDto);
        }

        return new PlannerResponseDto(planner);
    }


    public Long deletePlanner(Long id, String password) {
        // 해당 일정이 DB에 존재하는지 확인
        Planner planner = findPlanner(id);

        if(password.equals(planner.getPassword())) {
            //비밀번호 같아야 플래너 삭제
            plannerRepository.delete(planner);
        }
        return id;
    }

    private Planner findPlanner(Long id) {
        return plannerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));
    }


}
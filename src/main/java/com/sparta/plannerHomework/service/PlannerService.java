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
        return plannerRepository.findAllByOrderByModifiedAtDesc().stream().map(PlannerResponseDto::new).toList();
    }

    public List<PlannerResponseDto> getPlannersByKeyword(String keyword) {
        return plannerRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(PlannerResponseDto::new).toList();
    }

    @Transactional
    public Long updatePlanner(Long id, PlannerRequestDto requestDto) {
        // 해당 플랜이 DB에 존재하는지 확인
        Planner planner= findPlanner(id);

        // 플랜 내용 수정
        planner.update(requestDto);

        return id;
    }

    public Long deletePlanner(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Planner planner = findPlanner(id);

        // memo 삭제
        plannerRepository.delelte(id);

        return id;
    }

    private Planner findPlanner(Long id) {
        return plannerRepository.findById(id);
                /*요거 예외처리 어떻게 하는지 모르겠음....orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));*/
    }

    public List<PlannerResponseDto> getPlannersKeyword(String keyword) {
        return null;
    }
}
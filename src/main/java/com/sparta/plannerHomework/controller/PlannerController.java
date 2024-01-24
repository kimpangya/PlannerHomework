package com.sparta.plannerHomework.controller;

import com.sparta.plannerHomework.dto.PlannerRequestDto;
import com.sparta.plannerHomework.dto.PlannerResponseDto;
import com.sparta.plannerHomework.service.PlannerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlannerController {

    private final PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    //일정 작성 기능
    @PostMapping("/planners")
    public PlannerResponseDto createPlanner(@RequestBody PlannerRequestDto requestDto) {
        return plannerService.createPlanner(requestDto);
    }

    //전체 일정 목록 조회 기능
    @GetMapping("/planners")
    public List<PlannerResponseDto> getPlanners() {
        return plannerService.getPlanners();
    }

    //선택한 일정 조회 기능
    @GetMapping("/planners/contents")
    public List<PlannerResponseDto> getPlannersByKeyword(String keyword) {
        return plannerService.getPlannersByKeyword(keyword);
    }

    //선택한 일정 수정 기능
    @PatchMapping("/planners/{id}")
    public PlannerResponseDto updatePlanner(@PathVariable Long id, @RequestBody PlannerRequestDto requestDto){
        return plannerService.updatePlanner(id,requestDto);
    }

    //선택한 일정 삭제 기능
    @DeleteMapping("/planners/{id}/{password}")
    public Long deletePlanner(@PathVariable Long id, @PathVariable String password) {
        return plannerService.deletePlanner(id, password);
    }
}
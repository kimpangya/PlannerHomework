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

    @PostMapping("/planners")
    public PlannerResponseDto createPlanner(@RequestBody PlannerRequestDto requestDto) {
        return plannerService.createPlanner(requestDto);
    }

    @GetMapping("/planners")
    public List<PlannerResponseDto> getPlanners() {
        return plannerService.getPlanners();
    }

    @GetMapping("/planners/contents")
    public List<PlannerResponseDto> getPlannersByKeyword(String keyword) {
        return plannerService.getPlannersKeyword(keyword);
    }

    @PutMapping("/planners/{id}")
    public Long updatePlanner(@PathVariable Long id, @RequestBody PlannerRequestDto requestDto) {
        return plannerService.updatePlanner(id, requestDto);
    }

    @DeleteMapping("/planners/{id}")
    public Long deletePlanner(@PathVariable Long id) {
        return plannerService.deletePlanner(id);
    }
}
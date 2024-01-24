package com.sparta.plannerHomework.dto;

import com.sparta.plannerHomework.entity.Planner;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlannerResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String username;
  //  private String password;
    private String date;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PlannerResponseDto(Planner planner) {
        this.id= planner.getId();
        this.username= planner.getUsername();
        this.contents=planner.getContents();
        this.title= planner.getTitle();
   //     this.password=planner.getPassword();
        this.date=planner.getDate();
        this.createdAt=planner.getCreatedAt();
        this.modifiedAt=planner.getModifiedAt();
    }

}
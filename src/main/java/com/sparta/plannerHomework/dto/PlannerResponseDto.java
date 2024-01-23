package com.sparta.plannerHomework.dto;

import com.sparta.plannerHomework.entity.Planner;
import lombok.Getter;

@Getter
public class PlannerResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private String password;
    private String date;

    public PlannerResponseDto(Planner planner) {
        this.id= planner.getId();
        this.username= planner.getUsername();
        this.contents=planner.getContents();
        this.title= planner.getTitle();
        this.password=planner.getPassword();
        this.date=planner.getDate();
    }

    //생성자2
    public PlannerResponseDto(Long id, String title,String contents,String username, String password,String date){
        this.id=id;
        this.username=username;
        this.contents=contents;
        this.title=title;
        this.password=password;
        this.date=date;
    }

}
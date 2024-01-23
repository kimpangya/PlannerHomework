package com.sparta.plannerHomework.dto;

import lombok.Getter;

@Getter
public class PlannerRequestDto {
    private String title;
    private String contents;
    private String username;
    private String password;
    private String date;
}
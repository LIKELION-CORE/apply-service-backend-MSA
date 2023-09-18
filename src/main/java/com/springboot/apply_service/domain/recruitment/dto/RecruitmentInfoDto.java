package com.springboot.apply_service.domain.recruitment.dto;

import com.springboot.apply_service.domain.questions.dto.QuestionInfoDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RecruitmentInfoDto {
    private String title;
    private String part;
    private String activities;
    private String competencies;
    private String treatment;
    private String introduction;
    private String process;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<QuestionInfoDto> questions;
}

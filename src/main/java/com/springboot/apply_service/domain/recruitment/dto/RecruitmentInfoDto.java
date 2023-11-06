package com.springboot.apply_service.domain.recruitment.dto;

import com.springboot.apply_service.domain.questions.dto.QuestionInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentInfoDto {
    private Long rid;
    private String title;
    private String part;
    private String job;
    private String activities;
    private String competencies;
    private String treatment;
    private String introduction;
    private String process;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<QuestionInfoDto> questions;
}

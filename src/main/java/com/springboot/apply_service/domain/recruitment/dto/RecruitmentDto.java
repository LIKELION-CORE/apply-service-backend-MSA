package com.springboot.apply_service.domain.recruitment.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@Builder
public class RecruitmentDto {
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
}

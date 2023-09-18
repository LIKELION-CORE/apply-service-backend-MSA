package com.springboot.apply_service.domain.recruitment.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class RecruitmentDto {
    private Long rid;
    private String title;
    private String part;
    private String activities;
    private String competencies;
    private String treatment;
    private String introduction;
    private String process;
    private LocalDate startDate;
    private LocalDate endDate;
}

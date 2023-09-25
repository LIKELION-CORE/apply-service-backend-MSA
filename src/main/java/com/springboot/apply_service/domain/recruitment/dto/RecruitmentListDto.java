package com.springboot.apply_service.domain.recruitment.dto;

import com.springboot.apply_service.domain.questions.dto.QuestionInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RecruitmentListDto {
    private Long rid;
    private String title;
    private String part;
    private String job;
    private LocalDate startDate;
    private LocalDate endDate;
}

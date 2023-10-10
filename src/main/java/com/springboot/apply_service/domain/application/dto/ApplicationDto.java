package com.springboot.apply_service.domain.application.dto;

import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private Long aid;
    private Long rid;
    private Long uid;
    private String passState;
    private String mailState;
}

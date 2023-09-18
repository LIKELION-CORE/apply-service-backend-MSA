package com.springboot.apply_service.domain.application.dto;

import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
public class ApplicationDto {
    private Long aid;
    private Long rid;
    private Long uid;
    private String passState;
    private String mailState;
}

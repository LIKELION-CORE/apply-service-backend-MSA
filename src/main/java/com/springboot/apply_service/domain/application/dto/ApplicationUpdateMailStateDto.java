package com.springboot.apply_service.domain.application.dto;

import lombok.Data;

@Data
public class ApplicationUpdateMailStateDto {
    Long aid;
    String mailState;
}

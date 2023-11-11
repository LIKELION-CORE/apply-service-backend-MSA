package com.springboot.apply_service.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfoDto {
    private String aid;
    private String rid;
    private String userId;
    private String name;
    private String phone;
    private Integer studentId;
    private String department;
    private String email;
    private String part;
    private String job;
    private String passState;
    private String mailState;
}

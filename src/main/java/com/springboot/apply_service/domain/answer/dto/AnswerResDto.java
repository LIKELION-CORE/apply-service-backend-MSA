package com.springboot.apply_service.domain.answer.dto;

import lombok.Data;

@Data
public class AnswerResDto {
    Long aid;
    String question;
    String answer;
    String statue;
}

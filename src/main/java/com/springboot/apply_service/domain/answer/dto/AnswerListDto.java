package com.springboot.apply_service.domain.answer.dto;

import lombok.Data;

@Data
public class AnswerListDto {
    Long aid;
    Long qid;
    String content;

}

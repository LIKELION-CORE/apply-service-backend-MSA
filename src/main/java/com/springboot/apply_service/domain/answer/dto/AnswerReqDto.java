package com.springboot.apply_service.domain.answer.dto;

import lombok.Data;

@Data
public class AnswerReqDto {
    Long rid;
    Long qid;
    Long uid;
    Long aid;
    String content;
    String statue;
}

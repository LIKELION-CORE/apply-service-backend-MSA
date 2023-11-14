package com.springboot.apply_service.domain.answer.dto;

import lombok.Data;

@Data
public class AnswerInfoDto {
    private Long aid;
    private Long qid;
    //질문
    private String title;
    //답변
    private String content;
}

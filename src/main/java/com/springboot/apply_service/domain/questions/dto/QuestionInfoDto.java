package com.springboot.apply_service.domain.questions.dto;


import lombok.Data;

@Data
public class QuestionInfoDto {
    private Long qid;
    private String title;
    private String content;
    private Long minLen;
    private Long maxLen;
}

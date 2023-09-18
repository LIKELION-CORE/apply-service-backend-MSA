package com.springboot.apply_service.domain.questions.dto;


import lombok.Data;

@Data
public class QuestionsDto {
    private Long qid;
    private Long rid;
    private String title;
    private String content;
    private Long minLen;
    private Long maxLen;
}

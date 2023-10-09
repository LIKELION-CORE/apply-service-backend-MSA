package com.springboot.apply_service.domain.questions.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionInfoDto {
    private Long qid;
    private String title;
    private String content;
    private Long minLen;
    private Long maxLen;
}

package com.springboot.apply_service.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerReqDto {
    Long rid;
    Long qid;
    Long uid;
    Long aid;
    String content;
    String statue;
}

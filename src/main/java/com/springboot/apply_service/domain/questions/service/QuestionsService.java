package com.springboot.apply_service.domain.questions.service;


import com.springboot.apply_service.domain.questions.dto.QuestionsDto;
import com.springboot.apply_service.global.common.CommonResDto;

public interface QuestionsService {
    CommonResDto<QuestionsDto> createQuestion(QuestionsDto questionsDto);
    CommonResDto<QuestionsDto> updateQuestion(QuestionsDto questionsDto);
    CommonResDto<QuestionsDto> readQuestion(Long qid);
    CommonResDto<String> deleteQuestion(Long qid);
}

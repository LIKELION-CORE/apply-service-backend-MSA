package com.springboot.apply_service.domain.questions.dao;

import com.springboot.apply_service.domain.questions.dto.QuestionsDto;
import com.springboot.apply_service.global.common.CommonResDto;

import java.util.Optional;

public interface QuestionsDao {
    CommonResDto<QuestionsDto> createQuestion(QuestionsDto questionsDto);
    CommonResDto<QuestionsDto> updateQuestion(QuestionsDto questionsDto);
    CommonResDto<QuestionsDto> readQuestion(Long qid);
    CommonResDto<String> deleteQuestion(Long qid);
    CommonResDto<?> readQuestionsWithRid(Long rid);
}

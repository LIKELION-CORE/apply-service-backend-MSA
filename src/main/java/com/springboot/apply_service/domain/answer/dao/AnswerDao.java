package com.springboot.apply_service.domain.answer.dao;

import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.global.common.CommonResDto;

public interface AnswerDao {
    CommonResDto<AnswerResDto> createAnswer(AnswerReqDto answerReqDto);
    CommonResDto<AnswerResDto> updateAnswer(AnswerReqDto answerReqDto);
    CommonResDto<AnswerResDto> readAnswer(Long aid);
    CommonResDto<String> deleteAnswer(Long aid);

}

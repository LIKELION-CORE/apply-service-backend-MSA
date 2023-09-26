package com.springboot.apply_service.domain.answer.dao;

import com.springboot.apply_service.domain.answer.dto.AnswerListDto;
import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.global.common.CommonResDto;

import java.util.List;

public interface AnswerDao {
    CommonResDto<AnswerResDto> createAnswer(AnswerReqDto answerReqDto);
    CommonResDto<AnswerResDto> updateAnswer(AnswerReqDto answerReqDto);
    CommonResDto<AnswerResDto> readAnswer(Long aid);
    CommonResDto<String> deleteAnswer(Long aid);
    CommonResDto<String> submitAnswers(Long rid, Long uid);
    CommonResDto<List<AnswerListDto>> readUserAnswerWithRid(Long rid, Long uid);

}

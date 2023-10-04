package com.springboot.apply_service.domain.answer.service;

import com.springboot.apply_service.domain.answer.dto.AnswerListDto;
import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.global.common.CommonResDto;

import java.util.List;

public interface AnswerService {
    CommonResDto<AnswerResDto> createAnswer(AnswerReqDto answerReqDto);
    CommonResDto<AnswerResDto> updateAnswer(AnswerReqDto answerReqDto);
    CommonResDto<AnswerResDto> readAnswer(Long aid);
    CommonResDto<String> deleteAnswer(Long aid);
    CommonResDto<List<AnswerListDto>> readTempedAnswer(Long rid);
}

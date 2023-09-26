package com.springboot.apply_service.domain.answer.service.impl;

import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.domain.answer.dao.AnswerDao;
import com.springboot.apply_service.domain.answer.dto.AnswerListDto;
import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.domain.answer.service.AnswerService;
import com.springboot.apply_service.global.common.CommonResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    final private AnswerDao answerDao;
    private final UserServiceClient userServiceClient;

    @Autowired
    public AnswerServiceImpl(AnswerDao answerDao, UserServiceClient userServiceClient){
        this.answerDao = answerDao;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public CommonResDto<AnswerResDto> createAnswer(AnswerReqDto answerReqDto) {
        return answerDao.createAnswer(answerReqDto);
    }

    @Override
    public CommonResDto<AnswerResDto> updateAnswer(AnswerReqDto answerReqDto) {
        return answerDao.updateAnswer(answerReqDto);
    }

    @Override
    public CommonResDto<AnswerResDto> readAnswer(Long aid) {
        return answerDao.readAnswer(aid);
    }

    @Override
    public CommonResDto<String> deleteAnswer(Long aid) {
        return answerDao.deleteAnswer(aid);
    }

    @Override
    public CommonResDto<List<AnswerListDto>> readTempedAnswer(Long rid) {
        CommonResDto<MemberInfoResponseDto> memberInfo = userServiceClient.getInfo();

        return answerDao.readUserAnswerWithRid(rid, memberInfo.getData().getId());
    }
}

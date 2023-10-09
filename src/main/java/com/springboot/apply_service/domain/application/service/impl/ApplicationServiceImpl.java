package com.springboot.apply_service.domain.application.service.impl;

import com.netflix.discovery.converters.Auto;
import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.domain.answer.dao.AnswerDao;
import com.springboot.apply_service.domain.application.dao.ApplicationDao;
import com.springboot.apply_service.domain.application.dao.impl.ApplicationDaoImpl;
import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.domain.application.service.ApplicationService;
import com.springboot.apply_service.global.common.CommonResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    final private ApplicationDao applicationDao;
    final private UserServiceClient userServiceClient;
    final private AnswerDao answerDao;

    @Autowired
    public ApplicationServiceImpl(ApplicationDao applicationDao, UserServiceClient userServiceClient,
                                  AnswerDao answerDao){
        this.applicationDao = applicationDao;
        this.userServiceClient = userServiceClient;
        this.answerDao = answerDao;
    }

    @Override
    public CommonResDto<ApplicationDto> createApplication(ApplicationDto applicationDto) {
        CommonResDto<ApplicationDto> resDto;

        CommonResDto<MemberInfoResponseDto> memberInfo = userServiceClient.getInfo();
        Long uid = memberInfo.getData().getId();

        applicationDto.setUid(uid);
        applicationDto.setMailState("MAIL_PENDING");
        applicationDto.setPassState("APPLICATION_PENDING");

        CommonResDto<?> resApplicationDao = applicationDao.createApplication(applicationDto);
        CommonResDto<?> resAnswerDao = answerDao.submitAllAnswers(applicationDto.getRid(), uid);

        if(resApplicationDao.getCode() == -1)
            resDto = new CommonResDto<>(-1, "지원서 생성에 에러가 존재합니다.", null);

        else if(resAnswerDao.getCode() == -1)
            resDto = new CommonResDto<>(-1, "답변 저장에 에러가 존재합니다.", null);

        else
            resDto = new CommonResDto<>(1, "지원서 정상적으로 저장되었습니다.", null);

        return resDto;
    }
}

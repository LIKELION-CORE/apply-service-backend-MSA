package com.springboot.apply_service.domain.application.service.impl;

import com.netflix.discovery.converters.Auto;
import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.domain.answer.dao.AnswerDao;
import com.springboot.apply_service.domain.application.dao.ApplicationDao;
import com.springboot.apply_service.domain.application.dao.impl.ApplicationDaoImpl;
import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.domain.application.dto.ApplicationInfoDto;
import com.springboot.apply_service.domain.application.dto.ApplicationUpdateMailStateDto;
import com.springboot.apply_service.domain.application.dto.ApplicationUpdatePassStateDto;
import com.springboot.apply_service.domain.application.entity.Application;
import com.springboot.apply_service.domain.application.service.ApplicationService;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.domain.recruitment.repository.RecruitmentRepository;
import com.springboot.apply_service.global.common.CommonResDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    final private ApplicationDao applicationDao;
    final private UserServiceClient userServiceClient;
    final private AnswerDao answerDao;
    final private RecruitmentRepository recruitmentRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public ApplicationServiceImpl(ApplicationDao applicationDao,
                                  UserServiceClient userServiceClient,
                                  AnswerDao answerDao,
                                  RecruitmentRepository recruitmentRepository){
        this.applicationDao = applicationDao;
        this.userServiceClient = userServiceClient;
        this.answerDao = answerDao;
        this.recruitmentRepository = recruitmentRepository;
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

    @Override
    public CommonResDto<ApplicationInfoDto> getApplication(Long aid) {

        CommonResDto<ApplicationInfoDto> commonResDto;
        ApplicationDto applicationDto = applicationDao.getApplication(aid);
        Recruitment recruitment = recruitmentRepository.findById(applicationDto.getRid()).get();

        ApplicationInfoDto applicationInfoDto = mapper.map(applicationDto, ApplicationInfoDto.class);
        applicationInfoDto.setJob(recruitment.getJob());
        applicationInfoDto.setPart(recruitment.getPart());
        //applicationDto.getUid() -> 이걸로 값 가지고 오기
        //memberinfo 추가

        commonResDto = new CommonResDto<>(1, "데이터가 정상적으로 조회 되었습니다.", applicationInfoDto);

        return commonResDto;
    }

    @Override
    public CommonResDto<List<ApplicationInfoDto>> getApplicationByRid(Long rid) {
        //CommonResDto<MemberInfoResponseDto> memberInfo = userServiceClient.getMemberInfo();
        //memberInfo.getData()
        CommonResDto<List<ApplicationInfoDto>> commonResDto;
        List<ApplicationDto> applicationDtos = applicationDao.getApplicationByRid(rid);
        List<ApplicationInfoDto> applicationInfoDtos = new ArrayList<>();

        for(ApplicationDto applicationDto : applicationDtos ){
            Recruitment recruitment = recruitmentRepository.findById(applicationDto.getRid()).get();
            ApplicationInfoDto applicationInfoDto = mapper.map(applicationDto, ApplicationInfoDto.class);
            applicationInfoDto.setJob(recruitment.getJob());
            applicationInfoDto.setPart(recruitment.getPart());
            //applicationDto.getUid() -> 이걸로 값 가지고 오기
            applicationInfoDtos.add(applicationInfoDto);
        }

        commonResDto = new CommonResDto<>(1, "데이터가 정상적으로 조회 되었습니다.", applicationInfoDtos);

        return commonResDto;
    }

    @Override
    public CommonResDto<String> updateApplicationPassState(ApplicationUpdatePassStateDto applicationUpdatePassStateDto) {
        return applicationDao.updatePassState(applicationUpdatePassStateDto);
    }

    @Override
    public CommonResDto<String> updateApplicationMailState(ApplicationUpdateMailStateDto applicationUpdateMailStateDto) {
        return applicationDao.updateEmailState(applicationUpdateMailStateDto);
    }
}

package com.springboot.apply_service.domain.application.dao.impl;

import com.netflix.discovery.converters.Auto;
import com.springboot.apply_service.domain.application.dao.ApplicationDao;
import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.domain.application.entity.Application;
import com.springboot.apply_service.domain.application.repository.ApplicationRepository;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.domain.recruitment.repository.RecruitmentRepository;
import com.springboot.apply_service.global.common.CommonResDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationDaoImpl implements ApplicationDao {

    private final ApplicationRepository applicationRepository;
    private final RecruitmentRepository recruitmentRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public ApplicationDaoImpl(ApplicationRepository applicationRepository,
                              RecruitmentRepository recruitmentRepository){
        this.applicationRepository = applicationRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public CommonResDto<ApplicationDto> createApplication(ApplicationDto applicationDto) {
        Optional<Recruitment> recruitment = recruitmentRepository.findById(applicationDto.getRid());
        CommonResDto<ApplicationDto> commonResDto;

        if(recruitment.isPresent()){
            Application application = mapper.map(applicationDto, Application.class);
            application.setRid(recruitment.get());
            application = applicationRepository.save(application);
            ApplicationDto resApplication = mapper.map(application, ApplicationDto.class);
            resApplication.setRid(application.getRid().getRid());
            commonResDto = new CommonResDto<>(1, "데이터가 정상적으로 저장되었습니다.", resApplication);
        }else{
            commonResDto = new CommonResDto<>(-1, "공고가 조회되지 않습니다.", null);
        }

        return commonResDto;
    }
}

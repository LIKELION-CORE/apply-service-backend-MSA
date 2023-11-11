package com.springboot.apply_service.domain.application.dao.impl;

import com.netflix.discovery.converters.Auto;
import com.springboot.apply_service.domain.application.dao.ApplicationDao;
import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.domain.application.dto.ApplicationUpdateMailStateDto;
import com.springboot.apply_service.domain.application.dto.ApplicationUpdatePassStateDto;
import com.springboot.apply_service.domain.application.entity.Application;
import com.springboot.apply_service.domain.application.repository.ApplicationRepository;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.domain.recruitment.repository.RecruitmentRepository;
import com.springboot.apply_service.global.common.CommonResDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<ApplicationDto> getApplicationByRid(Long rid) {
        Recruitment recruitment = recruitmentRepository.getById(rid);
        List<Application> applications = applicationRepository.getAllByRid(recruitment);
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        for(Application application : applications){
            ApplicationDto applicationDto = mapper.map(application, ApplicationDto.class);
            applicationDtos.add(applicationDto);
        }
        return applicationDtos;
    }

    @Override
    public CommonResDto<String> updatePassState(ApplicationUpdatePassStateDto applicationUpdatePassStateDto) {
        Optional<Application> application = applicationRepository.findById(applicationUpdatePassStateDto.getAid());
        CommonResDto<String> commonResDto;
        if(application.isPresent()){
            Application newApplication = application.get();
            newApplication.setPassState(applicationUpdatePassStateDto.getPassState());
            applicationRepository.save(newApplication);
            commonResDto =  new CommonResDto<>(1, "지원서가 정상적으로 수정되었습니다.", null);
        }else{
            commonResDto = new CommonResDto<>(-1, "지원서가 존재하지 않습니다.", null);
        }
        return commonResDto;
    }

    @Override
    public CommonResDto<String> updateEmailState(ApplicationUpdateMailStateDto applicationUpdateMailStateDto) {
        Optional<Application> application = applicationRepository.findById(applicationUpdateMailStateDto.getAid());
        CommonResDto<String> commonResDto;
        if(application.isPresent()){
            Application newApplication = application.get();
            newApplication.setPassState(applicationUpdateMailStateDto.getMailState());
            applicationRepository.save(newApplication);
            commonResDto =  new CommonResDto<>(1, "지원서가 정상적으로 수정되었습니다.", null);
        }else{
            commonResDto = new CommonResDto<>(-1, "지원서가 존재하지 않습니다.", null);
        }
        return commonResDto;
    }
}

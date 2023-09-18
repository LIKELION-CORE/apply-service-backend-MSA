package com.springboot.apply_service.domain.application.service.impl;

import com.netflix.discovery.converters.Auto;
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

    @Autowired
    public ApplicationServiceImpl(ApplicationDao applicationDao){
        this.applicationDao = applicationDao;
    }

    @Override
    public CommonResDto<?> createApplication(ApplicationDto applicationDto) {
        return applicationDao.createApplication(applicationDto);
    }
}

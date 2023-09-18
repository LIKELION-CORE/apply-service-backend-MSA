package com.springboot.apply_service.domain.application.service;

import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.global.common.CommonResDto;

public interface ApplicationService {
    CommonResDto<?> createApplication(ApplicationDto applicationDto);
}

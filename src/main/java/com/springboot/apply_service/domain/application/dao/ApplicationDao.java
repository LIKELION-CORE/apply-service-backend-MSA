package com.springboot.apply_service.domain.application.dao;

import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.global.common.CommonResDto;

public interface ApplicationDao {
    CommonResDto<?> createApplication(ApplicationDto applicationDto);
}

package com.springboot.apply_service.domain.application.dao;

import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.domain.application.dto.ApplicationUpdateMailStateDto;
import com.springboot.apply_service.domain.application.dto.ApplicationUpdatePassStateDto;
import com.springboot.apply_service.global.common.CommonResDto;

import java.util.List;

public interface ApplicationDao {
    CommonResDto<ApplicationDto> createApplication(ApplicationDto applicationDto);
    List<ApplicationDto> getApplicationByRid(Long rid);
    CommonResDto<String> updatePassState(ApplicationUpdatePassStateDto applicationUpdatePassStateDto);
    CommonResDto<String> updateEmailState(ApplicationUpdateMailStateDto applicationUpdateMailStateDto);
}

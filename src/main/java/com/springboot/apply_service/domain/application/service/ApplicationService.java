package com.springboot.apply_service.domain.application.service;

import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.domain.application.dto.ApplicationInfoDto;
import com.springboot.apply_service.domain.application.dto.ApplicationUpdateMailStateDto;
import com.springboot.apply_service.domain.application.dto.ApplicationUpdatePassStateDto;
import com.springboot.apply_service.global.common.CommonResDto;

import java.util.List;

public interface ApplicationService {
    CommonResDto<ApplicationDto> createApplication(ApplicationDto applicationDto);
    CommonResDto<List<ApplicationInfoDto>> getApplicationByRid(Long rid);
    CommonResDto<String> updateApplicationPassState(ApplicationUpdatePassStateDto applicationUpdatePassStateDto);
    CommonResDto<String> updateApplicationMailState(ApplicationUpdateMailStateDto applicationUpdateMailStateDto);

}

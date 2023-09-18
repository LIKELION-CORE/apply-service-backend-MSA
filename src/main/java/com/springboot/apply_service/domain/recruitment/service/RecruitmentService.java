package com.springboot.apply_service.domain.recruitment.service;

import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.global.common.CommonResDto;

public interface RecruitmentService {
    CommonResDto<RecruitmentDto> createRecruitment(RecruitmentDto recruitmentDto, Long poster);
    CommonResDto<RecruitmentDto> readRecruitment(Long rid);
    CommonResDto<RecruitmentDto> updateRecruitment(RecruitmentDto recruitmentDto);
    CommonResDto<String> deleteRecruitment(Long rid);
    CommonResDto<RecruitmentInfoDto> readRecruitmentInfo(Long rid);
}

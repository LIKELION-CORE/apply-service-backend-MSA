package com.springboot.apply_service.domain.recruitment.service;

import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentListDto;
import com.springboot.apply_service.global.common.CommonResDto;

import java.util.List;

public interface RecruitmentService {
    CommonResDto<RecruitmentDto> createRecruitment(RecruitmentDto recruitmentDto, Long poster);
    CommonResDto<RecruitmentDto> readRecruitment(Long rid);
    CommonResDto<RecruitmentDto> updateRecruitment(RecruitmentDto recruitmentDto);
    CommonResDto<String> deleteRecruitment(Long rid);
    CommonResDto<RecruitmentInfoDto> readRecruitmentInfo(Long rid);
    CommonResDto<List<RecruitmentListDto>> readAllRecruitment();
    CommonResDto<RecruitmentInfoDto> createRecruitmentWithQuestions(RecruitmentInfoDto recruitmentInfoDto, Long poster);
}

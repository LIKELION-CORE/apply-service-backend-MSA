package com.springboot.apply_service.domain.recruitment.dao;

import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentListDto;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.global.common.CommonResDto;

import java.util.List;

public interface RecruitmentDao {
    CommonResDto<RecruitmentDto> createRecruitment(RecruitmentDto recruitmentDto, Long poster);
    CommonResDto<RecruitmentDto> readRecruitment(Long rid);
    CommonResDto<RecruitmentDto> updateRecruitment(RecruitmentDto recruitmentDto);
    CommonResDto<String> deleteRecruitment(Long rid);
    CommonResDto<RecruitmentInfoDto> readRecruitmentInfo(Long rid);
    CommonResDto<List<RecruitmentListDto>> readAllRecruitment();
}

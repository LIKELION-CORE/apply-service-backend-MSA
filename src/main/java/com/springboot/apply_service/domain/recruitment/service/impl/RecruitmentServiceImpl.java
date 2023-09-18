package com.springboot.apply_service.domain.recruitment.service.impl;

import com.springboot.apply_service.domain.recruitment.dao.RecruitmentDao;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.domain.recruitment.service.RecruitmentService;
import com.springboot.apply_service.global.common.CommonResDto;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    final private RecruitmentDao recruitmentDao;

    public RecruitmentServiceImpl(RecruitmentDao recruitmentDao){
        this.recruitmentDao = recruitmentDao;
    }

    @Override
    public CommonResDto<RecruitmentDto> createRecruitment(RecruitmentDto recruitmentDto, Long poster) {

        return recruitmentDao.createRecruitment(recruitmentDto, poster);
    }

    @Override
    public CommonResDto<RecruitmentDto> readRecruitment(Long rid) {
        return recruitmentDao.readRecruitment(rid);
    }

    @Override
    public CommonResDto<RecruitmentDto> updateRecruitment(RecruitmentDto recruitmentDto) {
        return recruitmentDao.updateRecruitment(recruitmentDto);
    }

    @Override
    public CommonResDto<String> deleteRecruitment(Long rid) {
        return recruitmentDao.deleteRecruitment(rid);
    }

    @Override
    public CommonResDto<RecruitmentInfoDto> readRecruitmentInfo(Long rid) {
        return recruitmentDao.readRecruitmentInfo(rid);
    }
}

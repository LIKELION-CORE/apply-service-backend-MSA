package com.springboot.apply_service.domain.recruitment.service.impl;

import com.springboot.apply_service.domain.questions.dao.QuestionsDao;
import com.springboot.apply_service.domain.questions.dto.QuestionInfoDto;
import com.springboot.apply_service.domain.questions.dto.QuestionsDto;
import com.springboot.apply_service.domain.recruitment.dao.RecruitmentDao;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentListDto;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.domain.recruitment.service.RecruitmentService;
import com.springboot.apply_service.global.common.CommonResDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    final private RecruitmentDao recruitmentDao;
    final private QuestionsDao questionsDao;
    ModelMapper mapper = new ModelMapper();

    public RecruitmentServiceImpl(RecruitmentDao recruitmentDao,QuestionsDao questionsDao){
        this.recruitmentDao = recruitmentDao;
        this.questionsDao = questionsDao;
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

    @Override
    public CommonResDto<List<RecruitmentListDto>> readAllRecruitment() {
        return recruitmentDao.readAllRecruitment();
    }

    @Override
    public CommonResDto<RecruitmentInfoDto> createRecruitmentWithQuestions(
            RecruitmentInfoDto recruitmentInfoDto, Long poster) {
        //RecruitmentDto recruitmentDto = mapper.map(recruitmentInfoDto, RecruitmentDto.class);
        List<QuestionInfoDto> questionInfoDtos = recruitmentInfoDto.getQuestions();

        //Rid 먼저
        Recruitment newRecruitment = mapper.map(recruitmentInfoDto, Recruitment.class);
        newRecruitment.setPoster(poster);
        newRecruitment = recruitmentDao.createRecruitmentWithQuestions(newRecruitment);

        for(QuestionInfoDto questionInfoDto : questionInfoDtos){
            QuestionsDto questionsDto = mapper.map(questionInfoDto, QuestionsDto.class);
            questionsDto.setRid(newRecruitment.getRid());
            questionsDao.createQuestion(questionsDto);
        }

        return recruitmentDao.readRecruitmentInfo(newRecruitment.getRid());
    }
}

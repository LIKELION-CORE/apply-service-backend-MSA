package com.springboot.apply_service.domain.recruitment.dao.impl;

import com.springboot.apply_service.domain.questions.dto.QuestionInfoDto;
import com.springboot.apply_service.domain.questions.entity.Questions;
import com.springboot.apply_service.domain.recruitment.dao.RecruitmentDao;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentListDto;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.domain.recruitment.repository.RecruitmentRepository;
import com.springboot.apply_service.global.common.CommonResDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecruitmentDaoImpl implements RecruitmentDao {

    private final RecruitmentRepository recruitmentRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public RecruitmentDaoImpl(RecruitmentRepository recruitmentRepository){
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public CommonResDto<RecruitmentDto> createRecruitment(RecruitmentDto recruitmentDto, Long poster) {

        Recruitment recruitment;

        recruitment = mapper.map(recruitmentDto, Recruitment.class);
        recruitment.setPoster(poster);
        recruitment = recruitmentRepository.save(recruitment);

        return new CommonResDto<>(1, "정상적으로 생성되었습니다.", mapper.map(recruitment, RecruitmentDto.class));
    }

    @Override
    public CommonResDto<RecruitmentDto> readRecruitment(Long rid) {

        Optional<Recruitment> savedRecruitment = recruitmentRepository.findById(rid);
        CommonResDto<RecruitmentDto> commonResDto;
        if(savedRecruitment.isPresent()){
            RecruitmentDto selectedRecruitment = mapper.map(savedRecruitment.get(), RecruitmentDto.class);
            commonResDto
                    = new CommonResDto<RecruitmentDto>(1, "데이터가 조회되었습니다.", selectedRecruitment);
        }
        else
            commonResDto
                    = new CommonResDto<RecruitmentDto>(-1, "데이터가 존재하지 않습니다.", null);

        return commonResDto;
    }

    @Override
    public CommonResDto<RecruitmentDto> updateRecruitment(RecruitmentDto recruitmentDto) {

        Optional<Recruitment> savedRecruitment = recruitmentRepository.findById(recruitmentDto.getRid());
        CommonResDto<RecruitmentDto> commonResDto;
        if(savedRecruitment.isPresent()){
            Recruitment updatedRecruitment = recruitmentRepository.save(
                    mapper.map(recruitmentDto, Recruitment.class)
            );

            commonResDto  = new CommonResDto<RecruitmentDto>(
                    1, "데이터 수정이 완료되었습니다.", mapper.map(updatedRecruitment, RecruitmentDto.class)
            );

        }
        else
            commonResDto  = new CommonResDto<RecruitmentDto>(-1, "데이터가 존재하지 않습니다.", null);


        return commonResDto;
    }

    @Override
    public CommonResDto<String> deleteRecruitment(Long rid) {
        Optional<Recruitment> savedRecruitment = recruitmentRepository.findById(rid);
        CommonResDto<String> commonResDto;
        if(savedRecruitment.isPresent()){
            recruitmentRepository.delete(savedRecruitment.get());
            commonResDto  = new CommonResDto<String>(1, "데이터 삭제가 완료되었습니다.", null);
        }
        else
            commonResDto  = new CommonResDto<String>(-1, "데이터가 존재하지 않습니다.", null);

        return commonResDto;
    }

    @Override
    public CommonResDto<RecruitmentInfoDto> readRecruitmentInfo(Long rid) {
        Optional<Recruitment> savedRecruitment = recruitmentRepository.findById(rid);
        CommonResDto<RecruitmentInfoDto> commonResDto;

        if(savedRecruitment.isPresent()){
            Recruitment recruitment = savedRecruitment.get();

            RecruitmentInfoDto recruitmentInfoDto;
            List<QuestionInfoDto> questions = new ArrayList<>();

            recruitmentInfoDto = mapper.map(recruitment, RecruitmentInfoDto.class);

            for(Questions question : recruitment.getQuestions()){
                QuestionInfoDto buff = mapper.map(question, QuestionInfoDto.class);
                questions.add(buff);
            }

            recruitmentInfoDto.setQuestions(questions);

            commonResDto
                    = new CommonResDto<RecruitmentInfoDto>(1,
                    "데이터가 조회되었습니다.",
                    recruitmentInfoDto);
        }
        else
            commonResDto
                    = new CommonResDto<RecruitmentInfoDto>(-1,
                    "데이터가 존재하지 않습니다.",
                    null);

        return commonResDto;
    }

    @Override
    public CommonResDto<List<RecruitmentListDto>> readAllRecruitment() {
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        List<RecruitmentListDto> resRecruitment = new ArrayList<>();
        CommonResDto<List<RecruitmentListDto>> commonResDto;


        if(recruitments.isEmpty()){
            commonResDto = new CommonResDto<>(-1, "데이터가 존재하지 않습니다.", null);
        }else {
            for(Recruitment tmp : recruitments){
                RecruitmentListDto recruitmentListDto = mapper.map(tmp, RecruitmentListDto.class);
                resRecruitment.add(recruitmentListDto);
            }
            commonResDto = new CommonResDto<>(1, "데이터가 정상적으로 반환되었습니다.", resRecruitment);
        }
        return commonResDto;
    }

    @Override
    public Recruitment createRecruitmentWithQuestions(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }
}

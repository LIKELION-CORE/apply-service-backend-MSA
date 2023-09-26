package com.springboot.apply_service.domain.answer.dao.impl;

import com.springboot.apply_service.domain.answer.dao.AnswerDao;
import com.springboot.apply_service.domain.answer.dto.AnswerListDto;
import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.domain.answer.entity.Answer;
import com.springboot.apply_service.domain.answer.repository.AnswerRepository;
import com.springboot.apply_service.domain.questions.repository.QuestionsRepository;
import com.springboot.apply_service.domain.recruitment.repository.RecruitmentRepository;
import com.springboot.apply_service.global.common.CommonResDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AnswerDaoImpl implements AnswerDao {

    final private AnswerRepository answerRepository;
    final private QuestionsRepository questionsRepository;
    final private RecruitmentRepository recruitmentRepository;
    ModelMapper mapper = new ModelMapper();
    @Autowired
    public AnswerDaoImpl(AnswerRepository answerRepository,
                         QuestionsRepository questionsRepository,
                         RecruitmentRepository recruitmentRepository){
        this.answerRepository = answerRepository;
        this.questionsRepository = questionsRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public CommonResDto<AnswerResDto> createAnswer(AnswerReqDto questionsDto) {

        Answer answer = mapper.map(questionsDto, Answer.class);
        answer = answerRepository.save(answer);

        AnswerResDto answerResDto = new AnswerResDto();
        answerResDto
                .setQuestion(questionsRepository
                        .getById(answer.getQid()).getContent());
        answerResDto.setAnswer(answer.getContent());
        answerResDto.setAid(answer.getAid());
        answerResDto.setStatue(answer.getStatue());

        return new CommonResDto<AnswerResDto>(1, "정상적으로 생성되었습니다.", answerResDto);
    }

    @Override
    public CommonResDto<AnswerResDto> updateAnswer(AnswerReqDto answerReqDto) {
        Optional<Answer> answer =answerRepository.findById(answerReqDto.getAid());
        CommonResDto<AnswerResDto> commonResDto;
        if(answer.isPresent()){

            Answer newAnswer = new Answer();
            newAnswer.setUid(answerReqDto.getUid());
            newAnswer.setQid(answerReqDto.getQid());
            newAnswer.setRid(answerReqDto.getRid());
            newAnswer.setAid(answerReqDto.getAid());

            newAnswer.setContent(answerReqDto.getContent());

            newAnswer = answerRepository.save(newAnswer);

            AnswerResDto answerResDto = new AnswerResDto();

            answerResDto.setAid(newAnswer.getAid());
            answerResDto.setQuestion(
                    questionsRepository.getById(newAnswer.getQid()).getTitle()
            );
            answerResDto.setAnswer(newAnswer.getContent());

            commonResDto  = new CommonResDto<AnswerResDto>(
                    1, "데이터 수정이 완료되었습니다.", answerResDto
            );
        }
        else
            commonResDto  = new CommonResDto<AnswerResDto>(-1, "데이터가 존재하지 않습니다.", null);

        return commonResDto;
    }

    @Override
    public CommonResDto<AnswerResDto> readAnswer(Long aid) {
        Optional<Answer> answer = answerRepository.findById(aid);
        CommonResDto<AnswerResDto> commonResDto;
        if(answer.isPresent()){

            AnswerResDto answerResDto = new AnswerResDto();

            answerResDto.setAid(answer.get().getAid());
            answerResDto.setQuestion(
                    questionsRepository.getById(answer.get().getQid()).getTitle()
            );
            answerResDto.setAnswer(answer.get().getContent());

            commonResDto  = new CommonResDto<AnswerResDto>(
                    1, "데이터 조회가 완료되었습니다.", answerResDto
            );
        }
        else
            commonResDto  = new CommonResDto<AnswerResDto>(-1, "데이터가 존재하지 않습니다.", null);

        return commonResDto;
    }

    @Override
    public CommonResDto<String> deleteAnswer(Long aid) {
        Optional<Answer> answer = answerRepository.findById(aid);
        CommonResDto<String> commonResDto;
        if(answer.isPresent()){

            answerRepository.delete(answer.get());

            commonResDto  = new CommonResDto<String>(
                    1, "데이터 삭제가 완료되었습니다.", null
            );
        }
        else
            commonResDto  = new CommonResDto<String>(-1, "데이터가 존재하지 않습니다.", null);

        return commonResDto;
    }

    @Override
    public CommonResDto<String> submitAnswers(Long rid, Long uid) {
        Optional<List<Answer>> answerList = answerRepository.findAnswersByRidAndUid(rid, uid);

        return null;
    }

    @Override
    public CommonResDto<List<AnswerListDto>> readUserAnswerWithRid(Long rid, Long uid) {
        Optional<List<Answer>> answerList = answerRepository.findAnswersByRidAndUid(rid, uid);
        CommonResDto<List<AnswerListDto>> commonResDto;
        if(answerList.isPresent()){
            List<AnswerListDto> answers = new ArrayList<>();

            for(Answer answer : answerList.get()){
                AnswerListDto tmp = mapper.map(answer, AnswerListDto.class);
                answers.add(tmp);
            }

            commonResDto = new CommonResDto<>(1, "임시 저장된 데이터가 존재합니다.", answers);

        }else{
            commonResDto = new CommonResDto<>(-1, "임시 저장된 데이터가 존재하지 않습니다.", null);
        }
        return commonResDto;
    }
}

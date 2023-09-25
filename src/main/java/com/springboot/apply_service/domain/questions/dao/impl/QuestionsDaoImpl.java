package com.springboot.apply_service.domain.questions.dao.impl;

import com.springboot.apply_service.domain.questions.dao.QuestionsDao;
import com.springboot.apply_service.domain.questions.dto.QuestionsDto;
import com.springboot.apply_service.domain.questions.entity.Questions;
import com.springboot.apply_service.domain.questions.repository.QuestionsQueryDslRepository;
import com.springboot.apply_service.domain.questions.repository.QuestionsRepository;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.domain.recruitment.repository.RecruitmentRepository;
import com.springboot.apply_service.global.common.CommonResDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;


@Component
public class QuestionsDaoImpl implements QuestionsDao {

    final private QuestionsRepository questionsRepository;
    final private RecruitmentRepository recruitmentRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Autowired
    public QuestionsDaoImpl(QuestionsRepository questionsRepository,
                            RecruitmentRepository recruitmentRepository){
        this.questionsRepository = questionsRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public CommonResDto<QuestionsDto> createQuestion(QuestionsDto questionsDto) {
        Recruitment recruitment = recruitmentRepository.getById(questionsDto.getRid());
        Questions questions = mapper.map(questionsDto, Questions.class);

        questions.setRid(recruitment);
        questions = questionsRepository.save(questions);

        QuestionsDto resQuestions = mapper.map(questions, QuestionsDto.class);
        resQuestions.setRid(questions.getRid().getRid());

        CommonResDto<QuestionsDto> commonResDto =
                new CommonResDto<>(1,
                        "질문이 정상적으로 등록되었습니다.",
                        resQuestions
        );
        return commonResDto;
    }

    @Override
    public CommonResDto<QuestionsDto> updateQuestion(QuestionsDto questionsDto) {
        Optional<Questions> questions =questionsRepository.findById(questionsDto.getQid());
        CommonResDto<QuestionsDto> commonResDto;
        if(questions.isPresent()){
            Questions newQuestions = mapper.map(questionsDto, Questions.class);
            newQuestions.setRid(
                    recruitmentRepository.getById(questionsDto.getRid())
            );

            Questions updatedQuestion = questionsRepository.save(newQuestions);
            QuestionsDto updatedQuestionDto = mapper.map(updatedQuestion, QuestionsDto.class);
            updatedQuestionDto.setRid(updatedQuestion.getRid().getRid());

            commonResDto  = new CommonResDto<QuestionsDto>(
                    1, "데이터 수정이 완료되었습니다.", updatedQuestionDto
            );
        }
        else
            commonResDto  = new CommonResDto<QuestionsDto>(-1, "데이터가 존재하지 않습니다.", null);

        return commonResDto;
    }

    @Override
    public CommonResDto<QuestionsDto> readQuestion(Long qid) {
        Optional<Questions> questions =questionsRepository.findById(qid);
        CommonResDto<QuestionsDto> commonResDto;
        if(questions.isPresent()){
            QuestionsDto updatedQuestions = mapper.map(questions.get(), QuestionsDto.class);
            updatedQuestions.setRid(questions.get().getRid().getRid());

            commonResDto  = new CommonResDto<QuestionsDto>(
                    1, "데이터 조회가 완료되었습니다.", updatedQuestions
            );
        }
        else
            commonResDto  = new CommonResDto<QuestionsDto>(-1, "데이터가 존재하지 않습니다.", null);

        return commonResDto;
    }

    @Override
    public CommonResDto<String> deleteQuestion(Long qid) {
        Optional<Questions> questions =questionsRepository.findById(qid);
        CommonResDto<String> commonResDto;
        if(questions.isPresent()){
            questionsRepository.delete(questions.get());
            commonResDto  = new CommonResDto<String>(
                    1, "데이터 삭제가 완료되었습니다.", null
            );
        }
        else
            commonResDto  = new CommonResDto<String>(-1, "데이터가 존재하지 않습니다.", null);

        return commonResDto;
    }

    @Override
    public CommonResDto<?> readQuestionsWithRid(Long rid) {

        Optional<Recruitment> savedRecruitment = recruitmentRepository.findById(rid);
        CommonResDto<RecruitmentDto> commonResDto;
        if(savedRecruitment.isPresent()){
            Optional<List<Questions>> questions = questionsRepository.readQuestionsWithRid(savedRecruitment.get());
            if(questions.isPresent()){

            }
//            RecruitmentDto selectedRecruitment = mapper.map(savedRecruitment.get(), RecruitmentDto.class);
//            commonResDto
//                    = new CommonResDto<RecruitmentDto>(1, "데이터가 조회되었습니다.", selectedRecruitment);
        }
        else
            commonResDto
                    = new CommonResDto<RecruitmentDto>(-1, "지원서 데이터가 존재하지 않습니다.", null);
        return null;

    }
}

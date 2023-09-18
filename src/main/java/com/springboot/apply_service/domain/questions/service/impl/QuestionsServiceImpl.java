package com.springboot.apply_service.domain.questions.service.impl;

import com.springboot.apply_service.domain.questions.dao.QuestionsDao;
import com.springboot.apply_service.domain.questions.dto.QuestionsDto;
import com.springboot.apply_service.domain.questions.service.QuestionsService;
import com.springboot.apply_service.global.common.CommonResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao questionsDao;

    @Autowired
    public QuestionsServiceImpl(QuestionsDao questionsDao){
        this.questionsDao = questionsDao;
    }
    @Override
    public CommonResDto<QuestionsDto> createQuestion(QuestionsDto questionsDto) {
        return questionsDao.createQuestion(questionsDto);
    }

    @Override
    public CommonResDto<QuestionsDto> updateQuestion(QuestionsDto questionsDto) {
        return questionsDao.updateQuestion(questionsDto);
    }

    @Override
    public CommonResDto<QuestionsDto> readQuestion(Long qid) {
        return questionsDao.readQuestion(qid);
    }

    @Override
    public CommonResDto<String> deleteQuestion(Long qid) {
        return questionsDao.deleteQuestion(qid);
    }
}

package com.springboot.apply_service.domain.answer.repository.impl;

import com.springboot.apply_service.config.Querydsl4RepositorySupport;
import com.springboot.apply_service.domain.answer.entity.Answer;
import com.springboot.apply_service.domain.answer.repository.AnswerQueryDslRepository;

import java.util.List;

public class AnswerRepositoryImpl extends Querydsl4RepositorySupport implements AnswerQueryDslRepository {

    public AnswerRepositoryImpl(){
        super(Answer.class);
    }

    @Override
    public List<Answer> finaAllAnswerByQidAndAid(Long rid, Long qid) {
        return null;
    }
}

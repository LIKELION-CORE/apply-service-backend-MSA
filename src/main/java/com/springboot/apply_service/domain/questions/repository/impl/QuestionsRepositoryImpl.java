package com.springboot.apply_service.domain.questions.repository.impl;

import com.springboot.apply_service.config.Querydsl4RepositorySupport;
import com.springboot.apply_service.domain.questions.entity.Questions;
import com.springboot.apply_service.domain.questions.repository.QuestionsQueryDslRepository;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;

import java.util.List;
import java.util.Optional;

import static com.springboot.apply_service.domain.questions.entity.QQuestions.questions;

public class QuestionsRepositoryImpl extends Querydsl4RepositorySupport implements QuestionsQueryDslRepository {
    public QuestionsRepositoryImpl() {
        super(Questions.class);
    }
    @Override
    public Optional<List<Questions>> readQuestionsWithRid(Recruitment rid) {
        return Optional.ofNullable(select(questions).from(questions)
                .where(questions.rid.eq(rid)).fetch());
    }
}

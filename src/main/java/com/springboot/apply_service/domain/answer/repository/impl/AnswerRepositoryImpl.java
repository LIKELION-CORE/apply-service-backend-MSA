package com.springboot.apply_service.domain.answer.repository.impl;

import com.springboot.apply_service.config.Querydsl4RepositorySupport;
import com.springboot.apply_service.domain.answer.entity.Answer;
import com.springboot.apply_service.domain.answer.repository.AnswerQueryDslRepository;

import java.util.List;
import java.util.Optional;

import static com.springboot.apply_service.domain.answer.entity.QAnswer.answer;

public class AnswerRepositoryImpl extends Querydsl4RepositorySupport implements AnswerQueryDslRepository {

    public AnswerRepositoryImpl(){
        super(Answer.class);
    }

    @Override
    public Optional<List<Answer>> findAllAnswerByQidAndAid(Long rid, Long qid) {

        return Optional.ofNullable(select(answer)
                .from(answer)
                .where(answer.rid.eq(rid).and(answer.qid.eq(qid))).fetch());
    }

    @Override
    public Optional<List<Answer>> findAnswersByRidAndUid(Long rid, Long uid) {
        return Optional.ofNullable(select(answer)
                .from(answer)
                .where(answer.rid.eq(rid).and(answer.uid.eq(uid))).fetch());
    }

    @Override
    public Optional<List<Answer>> findAnswerByQidAndUid(Long qid, Long uid) {
        return Optional.ofNullable(select(answer).
                from(answer).where(answer.qid.eq(qid).and(answer.uid.eq(uid))).fetch()
        );
    }
}

package com.springboot.apply_service.domain.answer.repository;

import com.springboot.apply_service.domain.answer.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerQueryDslRepository {
    Optional<List<Answer>> findAllAnswerByQidAndAid(Long rid, Long qid);
    Optional<List<Answer>> findAnswersByRidAndUid(Long rid, Long uid);
    Optional<List<Answer>> findAnswerByQidAndUid(Long qid, Long uid);
    //Optional<List<Answer>> findAllAnswersByRidAndQidAndUid(Long rid, Long qid, Long uid);
}

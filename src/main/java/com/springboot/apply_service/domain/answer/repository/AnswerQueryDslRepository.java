package com.springboot.apply_service.domain.answer.repository;

import com.springboot.apply_service.domain.answer.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerQueryDslRepository {
    List<Answer> finaAllAnswerByQidAndAid(Long rid, Long qid);
}

package com.springboot.apply_service.domain.answer.repository;

import com.springboot.apply_service.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerQueryDslRepository {
}

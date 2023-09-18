package com.springboot.apply_service.domain.questions.repository;

import com.springboot.apply_service.domain.questions.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
}

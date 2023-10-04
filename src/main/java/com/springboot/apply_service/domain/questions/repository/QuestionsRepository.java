package com.springboot.apply_service.domain.questions.repository;

import com.springboot.apply_service.domain.questions.entity.Questions;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Questions, Long>, QuestionsQueryDslRepository{
    Optional<List<Questions>> readQuestionsWithRid(Recruitment rid);
}

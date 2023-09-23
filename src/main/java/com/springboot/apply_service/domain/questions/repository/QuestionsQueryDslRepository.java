package com.springboot.apply_service.domain.questions.repository;

import com.springboot.apply_service.domain.questions.entity.Questions;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;

import java.util.List;
import java.util.Optional;

public interface QuestionsQueryDslRepository {
    Optional<List<Questions>> readQuestionsWithRid(Recruitment rid);
}

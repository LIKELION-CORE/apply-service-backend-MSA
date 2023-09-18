package com.springboot.apply_service.domain.recruitment.repository;

import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}

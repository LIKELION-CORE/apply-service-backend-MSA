package com.springboot.apply_service.domain.application.repository;

import com.springboot.apply_service.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}

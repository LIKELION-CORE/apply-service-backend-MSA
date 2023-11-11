package com.springboot.apply_service.domain.application.repository;

import com.springboot.apply_service.domain.application.entity.Application;
import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> getAllByRid(Recruitment rid);
}

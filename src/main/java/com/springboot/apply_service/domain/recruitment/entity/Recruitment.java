package com.springboot.apply_service.domain.recruitment.entity;

import com.springboot.apply_service.domain.questions.entity.Questions;
import com.springboot.apply_service.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//모집 공고 entity
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "recruitment")
public class Recruitment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @Column(nullable = false)
    private Long poster;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private String part;

    @Column(nullable = false, length=1000)
    private String activities;

    @Column(nullable = false, length=1000)
    private String competencies;

    @Column(nullable = false, length=1000)
    private String treatment;

    @Column(nullable = false, length=1000)
    private String introduction;

    @Column(nullable = false, length=1000)
    private String process;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "rid", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Questions> questions = new ArrayList<>();
}

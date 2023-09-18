package com.springboot.apply_service.domain.questions.entity;

import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

//질문 entity
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "questions")
public class Questions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qid;

    @ManyToOne
    @JoinColumn(name = "rid")
    @ToString.Exclude
    private Recruitment rid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long minLen;

    @Column(nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maxLen;

}

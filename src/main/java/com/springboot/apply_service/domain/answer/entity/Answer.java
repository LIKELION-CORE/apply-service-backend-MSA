package com.springboot.apply_service.domain.answer.entity;

import com.springboot.apply_service.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "answer")
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column(nullable = false)
    private Long rid;

    @Column(nullable = false)
    private Long qid;

    @Column(nullable = false)
    private Long uid;

    @Column(nullable = false)
    private String statue;

    @Column(nullable = false)
    private String content;
}

package com.springboot.apply_service.domain.application.entity;

import com.springboot.apply_service.domain.recruitment.entity.Recruitment;
import com.springboot.apply_service.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "application")
public class Application extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @ManyToOne
    @JoinColumn(name = "rid")
    @ToString.Exclude
    private Recruitment rid;

    @Column(nullable = false)
    private Long uid;

    @Column(nullable = false)
    private String passState;

    @Column(nullable = false)
    private String mailState;
}
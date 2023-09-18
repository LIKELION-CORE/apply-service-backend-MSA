package com.springboot.apply_service.global.kafka.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
public class Payload {
    //private Long aid;
    private Long rid;
    private Long qid;
    private Long uid;
    private String content;
}

package com.springboot.apply_service.global.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KafkaAnswerDto {
    private Schema schema;
    private Payload payload;
}

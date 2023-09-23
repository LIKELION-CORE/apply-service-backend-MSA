package com.springboot.apply_service.global.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.global.kafka.dto.Field;
import com.springboot.apply_service.global.kafka.dto.KafkaAnswerDto;
import com.springboot.apply_service.global.kafka.dto.Payload;
import com.springboot.apply_service.global.kafka.dto.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class KafkaAnswerProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(
            new Field("int64", true, "rid"),
            new Field("int64", true, "qid"),
            new Field("int64", true, "uid"),
            new Field("string", true, "content")
    );
    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("answer")
            .build();

    @Autowired
    public KafkaAnswerProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public AnswerResDto send(String topic, AnswerReqDto answerReqDto){

        Payload payload = Payload.builder()
                .rid(answerReqDto.getRid())
                .uid(answerReqDto.getUid())
                .qid(answerReqDto.getQid())
                .content(answerReqDto.getContent())
                .build();

        KafkaAnswerDto kafkaAnswerDto = new KafkaAnswerDto(schema, payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try{
            jsonInString = mapper.writeValueAsString(kafkaAnswerDto);
        }catch(JsonProcessingException ex){
            ex.printStackTrace();
        }
        //jsonInString = "TestString";

        ListenableFuture<SendResult<String, String>> ret = kafkaTemplate.send(topic, jsonInString);
        log.info("Order Producer sent data from the Order microservice: " + kafkaAnswerDto);


        return null;
    }
}

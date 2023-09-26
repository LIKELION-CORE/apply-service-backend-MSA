package com.springboot.apply_service.domain.answer.controller;

import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.domain.answer.dto.AnswerListDto;
import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.domain.answer.service.AnswerService;
import com.springboot.apply_service.global.common.CommonResDto;
import com.springboot.apply_service.global.kafka.producer.KafkaAnswerProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final KafkaAnswerProducer kafkaAnswerProducer;
    private final UserServiceClient userServiceClient;

    @Autowired
    public AnswerController(AnswerService answerService, KafkaAnswerProducer kafkaAnswerProducer,
                            UserServiceClient userServiceClient){
        this.answerService = answerService;
        this.kafkaAnswerProducer = kafkaAnswerProducer;
        this.userServiceClient = userServiceClient;
    }
    @GetMapping()
    public ResponseEntity<CommonResDto<AnswerResDto>> readAnswer(Long aid) {

        CommonResDto<AnswerResDto> commonResDto = answerService.readAnswer(aid);


        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);
    }
    @PostMapping()
    public ResponseEntity<CommonResDto<AnswerResDto>> createAnswer(@RequestBody AnswerReqDto answerReqDto) {

        //AnswerResDto answer = kafkaAnswerProducer.send("answer", answerReqDto);
        CommonResDto<MemberInfoResponseDto> memberInfo = userServiceClient.getInfo();
        Long uid = memberInfo.getData().getId();
        answerReqDto.setUid(uid);
        answerReqDto.setStatue("ANSWER_TEMPTED");
        CommonResDto<AnswerResDto> commonResDto = answerService.createAnswer(answerReqDto);


        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResDto);
        //return null;

    }
    @PutMapping()
    public ResponseEntity<CommonResDto<AnswerResDto>> updateAnswer(
            @RequestBody AnswerReqDto answerReqDto) throws Exception {

        CommonResDto<AnswerResDto> commonResDto = answerService.updateAnswer(answerReqDto);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);

    }
    @GetMapping("/tempedAnswer")
    public ResponseEntity<CommonResDto<List<AnswerListDto>>> readTempedAnswer(Long rid) {

        CommonResDto<List<AnswerListDto>> commonResDto = answerService.readTempedAnswer(rid);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResDto);
    }
    @DeleteMapping()
    public ResponseEntity<CommonResDto<String>> deleteAnswer(Long aid) throws Exception {
        CommonResDto<String> commonResDto = answerService.deleteAnswer(aid);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);
    }
}
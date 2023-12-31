package com.springboot.apply_service.domain.application.controller;

import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.domain.application.service.ApplicationService;
import com.springboot.apply_service.global.common.CommonResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    final private ApplicationService applicationService;
    final private UserServiceClient userServiceClient;

    @Autowired
    public ApplicationController(ApplicationService applicationService,
                                 UserServiceClient userServiceClient){
        this.applicationService = applicationService;
        this.userServiceClient = userServiceClient;
    }

    @PostMapping()
    public ResponseEntity<CommonResDto<?>> createApplication(
            @RequestBody ApplicationDto applicationDto) {

//        kafkaAnswerProducer.send("answer", answerReqDto);
//        return null;
//        CommonResDto<MemberInfoResponseDto> memberInfo = userServiceClient.getInfo();
//        Long uid = memberInfo.getData().getId();
//        applicationDto.setUid(uid);
        CommonResDto<?> commonResDto = applicationService.createApplication(applicationDto);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResDto);

    }
}

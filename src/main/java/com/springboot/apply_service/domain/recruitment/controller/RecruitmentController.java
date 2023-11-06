package com.springboot.apply_service.domain.recruitment.controller;

import com.springboot.apply_service.VO.Greeting;
import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.domain.auth.JwtProvider;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.domain.recruitment.service.RecruitmentService;
import com.springboot.apply_service.global.common.CommonResDto;
import io.micrometer.core.annotation.Timed;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;
    //private final JwtProvider jwtProvider;
    private final UserServiceClient userServiceClient;
    private Greeting greeting;
    private Environment environment;

    @Autowired
    public RecruitmentController(RecruitmentService recruitmentService,
                                 UserServiceClient userServiceClient,
                                 Greeting greeting,
                                 Environment environment) {
        this.recruitmentService = recruitmentService;
        //this.jwtProvider = jwtProvider;
        this.userServiceClient = userServiceClient;
        this.greeting = greeting;
        this.environment = environment;
    }

    @GetMapping("/welcome")
    @Timed(value="users.status", longTask = true)
    public String status(){

//        return String.format("It's Working in User Service on PORT %s"
//                , env.getProperty("local.server.port"));

        return String.format("It's Working in User Service"
                + ", port(greeting.message)=" + environment.getProperty("greeting.message")
//                + ", port(server.port)=" + environment.getProperty("server.port")
//                + ", token secret=" + environment.getProperty("token.secret")
//                + ", token expiration time=" + environment.getProperty("token.expiration_time")
        );

    }

//    @GetMapping("/welcome")
//    @Timed(value="users.welcome", longTask = true)
//    public String welcome(){
//        return greeting.getMessage();
//    }
    @GetMapping()
    public ResponseEntity<CommonResDto<RecruitmentDto>> readRecruitment(Long rid) {

        CommonResDto<RecruitmentDto> commonResDto = recruitmentService.readRecruitment(rid);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);
    }
    @GetMapping("/getInfo")
    public ResponseEntity<CommonResDto<RecruitmentInfoDto>> readRecruitmentInfo(Long rid) {

        CommonResDto<RecruitmentInfoDto> commonResDto = recruitmentService.readRecruitmentInfo(rid);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> readAllRecruitment() {

        CommonResDto<?> commonResDto = recruitmentService.readAllRecruitment();

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);
    }
    @PostMapping()
    public ResponseEntity<CommonResDto<RecruitmentDto>> createRecruitment(@RequestBody RecruitmentDto recruitmentDto) {
        CommonResDto<MemberInfoResponseDto> memberInfo = userServiceClient.getInfo();
        Long poster = memberInfo.getData().getId();

        //System.out.println(memberInfo.getBody().getUserId());

        CommonResDto<RecruitmentDto> commonResDto = recruitmentService.createRecruitment(recruitmentDto, poster);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResDto);
    }
    @PutMapping()
    public ResponseEntity<CommonResDto<RecruitmentDto>> updateRecruitment(
            @RequestBody RecruitmentDto recruitmentDto) throws Exception {

        CommonResDto<RecruitmentDto> commonResDto = recruitmentService.updateRecruitment(recruitmentDto);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);

    }
    @DeleteMapping()
    public ResponseEntity<CommonResDto<String>> deleteRecruitment(Long rid) throws Exception {
        CommonResDto<String> commonResDto = recruitmentService.deleteRecruitment(rid);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);
    }
}

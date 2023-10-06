package com.springboot.apply_service.domain.recruitment.controller;

import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.domain.auth.JwtProvider;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.domain.recruitment.service.RecruitmentService;
import com.springboot.apply_service.global.common.CommonResDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public RecruitmentController(RecruitmentService recruitmentService,
                                 /*JwtProvider jwtProvider,*/ UserServiceClient userServiceClient) {
        this.recruitmentService = recruitmentService;
        //this.jwtProvider = jwtProvider;
        this.userServiceClient = userServiceClient;
    }
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
    public ResponseEntity<CommonResDto<RecruitmentDto>> createRecruitment(HttpServletRequest httpServletRequest,
                                                                          @RequestBody RecruitmentDto recruitmentDto) {
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

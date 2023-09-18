package com.springboot.apply_service.domain.questions.controller;

import com.springboot.apply_service.domain.questions.dto.QuestionsDto;
import com.springboot.apply_service.domain.questions.service.QuestionsService;
import com.springboot.apply_service.global.common.CommonResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsService questionsService;

    @Autowired
    public QuestionsController(QuestionsService questionsService){

        this.questionsService = questionsService;
    }
    @GetMapping()
    public ResponseEntity<CommonResDto<QuestionsDto>> readRecruitment(Long qid) {

        CommonResDto<QuestionsDto> commonResDto = questionsService.readQuestion(qid);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);
    }
    @PostMapping()
    public ResponseEntity<CommonResDto<QuestionsDto>> createRecruitment(@RequestBody QuestionsDto questionsDto) {

        CommonResDto<QuestionsDto> commonResDto = questionsService.createQuestion(questionsDto);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResDto);
    }
    @PutMapping()
    public ResponseEntity<CommonResDto<QuestionsDto>> updateRecruitment(
            @RequestBody QuestionsDto questionsDto) throws Exception {

        CommonResDto<QuestionsDto> commonResDto = questionsService.updateQuestion(questionsDto);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);

    }
    @DeleteMapping()
    public ResponseEntity<CommonResDto<String>> deleteRecruitment(Long qid) throws Exception {
        CommonResDto<String> commonResDto = questionsService.deleteQuestion(qid);

        if(commonResDto.getCode() == 1)
            return ResponseEntity.status(HttpStatus.OK).body(commonResDto);

        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResDto);
    }
}

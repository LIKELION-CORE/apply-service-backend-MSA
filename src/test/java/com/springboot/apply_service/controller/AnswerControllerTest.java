package com.springboot.apply_service.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.config.LocalDateAdapter;
import com.springboot.apply_service.domain.answer.controller.AnswerController;
import com.springboot.apply_service.domain.answer.dto.AnswerListDto;
import com.springboot.apply_service.domain.answer.dto.AnswerReqDto;
import com.springboot.apply_service.domain.answer.dto.AnswerResDto;
import com.springboot.apply_service.domain.answer.service.AnswerService;
import com.springboot.apply_service.domain.answer.service.impl.AnswerServiceImpl;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentListDto;
import com.springboot.apply_service.global.common.CommonResDto;
import com.springboot.apply_service.global.kafka.producer.KafkaAnswerProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AnswerServiceImpl answerService;
    @MockBean
    UserServiceClient userServiceClient;
    @MockBean
    KafkaAnswerProducer kafkaAnswerProducer;

    //http://localhost:8000/apply-service/answer
    @Test
    @DisplayName("Create Answer")
    void createAnswerTest() throws Exception{

        AnswerReqDto answerReqDto = AnswerReqDto.builder()
                .qid(1L)
                .uid(1L)
                .rid(1L)
                .content("answer1")
                .statue("ANSWER_TEMPTED")
                .build();

        AnswerResDto answerResDto = AnswerResDto.builder()
                .aid(1L)
                .answer("answer1")
                .question("question1")
                .statue("ANSWER_TEMPTED")
                .build();

        CommonResDto<AnswerResDto> commonResDto = new CommonResDto<>(1, "", answerResDto);

        CommonResDto<MemberInfoResponseDto> tmpMemberInfo = new CommonResDto<>(
                1,
                "",
                MemberInfoResponseDto.builder().userId(1L).build());

        Mockito.when(answerService.createAnswer(answerReqDto)).thenReturn(commonResDto);
        Mockito.when(userServiceClient.getInfo()).thenReturn(tmpMemberInfo);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String content = gson.toJson(answerReqDto);

        mockMvc.perform(
                        post("/answer")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(
                        "$.code").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andDo(print());

        verify(answerService).createAnswer(answerReqDto);
    }

    //http://localhost:8000/apply-service/answer/tempedAnswer?rid=11
    @Test
    @DisplayName("Read Temped Answer")
    void readTempedAnswerTest() throws Exception{

        List<AnswerListDto> answerListDtos = new ArrayList<>();
        answerListDtos.add(
                AnswerListDto.builder()
                        .aid(1L)
                        .qid(1L)
                        .content("answer1")
                        .build()
        );
        answerListDtos.add(
                AnswerListDto.builder()
                        .aid(2L)
                        .qid(2L)
                        .content("answer2")
                        .build()
        );

        CommonResDto<List<AnswerListDto>> commonResDto = new CommonResDto<>(1, "", answerListDtos);

        Mockito.when(answerService.readTempedAnswer(1L)).thenReturn(commonResDto);

        mockMvc.perform(
                        get("/answer/tempedAnswer?rid=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.code").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andDo(print());

        verify(answerService).readTempedAnswer(1L);
    }
}

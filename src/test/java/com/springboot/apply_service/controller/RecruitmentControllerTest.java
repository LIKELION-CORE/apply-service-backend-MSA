package com.springboot.apply_service.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.config.LocalDateAdapter;
import com.springboot.apply_service.domain.questions.dto.QuestionInfoDto;
import com.springboot.apply_service.domain.recruitment.controller.RecruitmentController;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentInfoDto;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentListDto;
import com.springboot.apply_service.domain.recruitment.service.RecruitmentService;
import com.springboot.apply_service.domain.recruitment.service.impl.RecruitmentServiceImpl;
import com.springboot.apply_service.global.common.CommonResDto;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecruitmentController.class)
class RecruitmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    RecruitmentServiceImpl recruitmentService;
    @MockBean
    UserServiceClient userServiceClient;

    @Test
    @DisplayName("Get Recruitment List Test")
    void getRecruitmentListTest() throws Exception{

        List<RecruitmentListDto> list = new ArrayList<>();
        list.add(
                new RecruitmentListDto(1L,
                        "title1",
                        "part1",
                        "job1",LocalDate.now(), LocalDate.now()));
        list.add(
                new RecruitmentListDto(2L,
                        "title2",
                        "part2",
                        "job2",LocalDate.now(), LocalDate.now()));

        CommonResDto<List<RecruitmentListDto>> tmp = new CommonResDto<>(1, "정상적으로 조회되었습니다.", list);
        Mockito.when(recruitmentService.readAllRecruitment()).thenReturn(tmp);

        mockMvc.perform(
                        get("/recruitment/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.code").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andDo(print());
        
        verify(recruitmentService).readAllRecruitment();
    }

    //localhost:8000/apply-service/recruitment
    @Test
    @DisplayName("Create Recruitment Test")
    void createRecruitmentTest() throws Exception{

        RecruitmentDto recruitmentDto = RecruitmentDto.builder()
                .rid(12L)
                .title("프론트엔드 아기사자를 모집합니다.")
                .part("FRONTEND")
                .job("LION")
                .activities("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .competencies("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .treatment("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .introduction("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .process("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .build();

        CommonResDto<RecruitmentDto> tmpCreate = new CommonResDto<>(
                1,
                "정상적으로 조회되었습니다.",
                recruitmentDto);
        CommonResDto<MemberInfoResponseDto> tmpMemberInfo = new CommonResDto<>(
                1,
                "",
                MemberInfoResponseDto.builder().userId(1L).build());

        Mockito.when(recruitmentService.createRecruitment(recruitmentDto, 1L)).thenReturn(tmpCreate);
        Mockito.when(userServiceClient.getInfo()).thenReturn(tmpMemberInfo);

        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String content = gson.toJson(recruitmentDto);

        mockMvc.perform(
                        post("/recruitment")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andExpect(jsonPath(
                        "$.code").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andDo(print());

        verify(recruitmentService).createRecruitment(recruitmentDto, 1L);
    }

    //http://localhost:8000/apply-service/recruitment/getInfo?rid=12
    @Test
    @DisplayName("Read Recruitment Test")
    void readRecruitmentTest() throws Exception{

        List<QuestionInfoDto> questionInfoDtos = new ArrayList<>();

        questionInfoDtos.add(QuestionInfoDto.builder()
                .qid(1L)
                .title("title1")
                .content("content")
                .minLen(500L)
                .maxLen(500L)
                .build()
        );
        questionInfoDtos.add(QuestionInfoDto.builder()
                .qid(2L)
                .title("title2")
                .content("content")
                .minLen(500L)
                .maxLen(500L)
                .build()
        );

        RecruitmentInfoDto recruitmentInfoDto = RecruitmentInfoDto.builder()
                .rid(12L)
                .title("프론트엔드 아기사자를 모집합니다.")
                .part("FRONTEND")
                .job("LION")
                .activities("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .competencies("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .treatment("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .introduction("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .process("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .questions(questionInfoDtos)
                .build();

        CommonResDto<RecruitmentInfoDto> commonResDto = new CommonResDto<>(1, "", recruitmentInfoDto);

        Mockito.when(recruitmentService.readRecruitmentInfo(12L)).thenReturn(commonResDto);
        mockMvc.perform(get("/recruitment/getInfo?rid=12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.code").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andDo(print());

        verify(recruitmentService).readRecruitmentInfo(12L);
    }

    //http://localhost:8000/apply-service/answer

    //http://localhost:8000/apply-service/answer/tempedAnswer?rid=11

    //http://localhost:8000/apply-service/application
}

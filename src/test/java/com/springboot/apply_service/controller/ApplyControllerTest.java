package com.springboot.apply_service.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.config.LocalDateAdapter;
import com.springboot.apply_service.domain.answer.dto.AnswerListDto;
import com.springboot.apply_service.domain.application.controller.ApplicationController;
import com.springboot.apply_service.domain.application.dto.ApplicationDto;
import com.springboot.apply_service.domain.application.service.ApplicationService;
import com.springboot.apply_service.domain.application.service.impl.ApplicationServiceImpl;
import com.springboot.apply_service.domain.recruitment.controller.RecruitmentController;
import com.springboot.apply_service.global.common.CommonResDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationController.class)
public class ApplyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ApplicationServiceImpl applicationService;
    @MockBean
    UserServiceClient userServiceClient;

    //http://localhost:8000/apply-service/application
    @Test
    @DisplayName("Create Application")
    void createApplication() throws Exception{

        ApplicationDto applicationDto = ApplicationDto.builder()
                .rid(1L)
                .aid(1L)
                .uid(1L)
                .passState("APPLICATION_PENDING")
                .mailState("MAIL_PENDING")
                .build();
        CommonResDto<ApplicationDto> commonResDto = new CommonResDto<>(1, "", applicationDto);

        Mockito.when(applicationService.createApplication(applicationDto)).thenReturn(commonResDto);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String content = gson.toJson(applicationDto);

        mockMvc.perform(post("/application")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(
                        "$.code").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andDo(print());

        verify(applicationService).createApplication(applicationDto);
    }
}

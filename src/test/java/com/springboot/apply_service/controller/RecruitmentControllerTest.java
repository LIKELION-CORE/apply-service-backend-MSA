package com.springboot.apply_service.controller;


import com.springboot.apply_service.client.UserServiceClient;
import com.springboot.apply_service.domain.recruitment.controller.RecruitmentController;
import com.springboot.apply_service.domain.recruitment.dto.RecruitmentListDto;
import com.springboot.apply_service.domain.recruitment.service.RecruitmentService;
import com.springboot.apply_service.domain.recruitment.service.impl.RecruitmentServiceImpl;
import com.springboot.apply_service.global.common.CommonResDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
//    @Autowired
//    public RecruitmentControllerTest(MockMvc mockMvc, RecruitmentServiceImpl recruitmentService){
//        this.mockMvc = mockMvc;
//        this.recruitmentService = recruitmentService;
//    }

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
        //CommonResDto<List<RecruitmentListDto>> tmp = CommonResDto.builder().data(list).code(1).message("정상 적으로 조회되었습니다.").build();
        CommonResDto<List<RecruitmentListDto>> tmp = new CommonResDto<>(1, "정상적으로 조회되었습니다.", list);
        //given(recruitmentService.readAllRecruitment()).willReturn(tmp).getMock();
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
}

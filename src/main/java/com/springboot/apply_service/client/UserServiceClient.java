package com.springboot.apply_service.client;

import com.springboot.apply_service.VO.ResponseOrder;
import com.springboot.apply_service.client.dto.MemberInfoResponseDto;
import com.springboot.apply_service.global.common.CommonResDto;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@FeignClient(name = "user-service", configuration = HeaderConfiguration.class)
public interface UserServiceClient {
//    @PostMapping("/user-service/info")
//    List<ResponseOrder> getOrders(@PathVariable String userId);
    @PostMapping("/api/v1/member/info")
    //@Headers("Content-Type: application/json")
    CommonResDto<MemberInfoResponseDto> getInfo();
    @GetMapping("/api/v1/member")
        //@Headers("Content-Type: application/json")
    CommonResDto<MemberInfoResponseDto> getMemberInfo();
}

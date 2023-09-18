package com.springboot.apply_service.client.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoResponseDto {
    private Long id;

    @Builder
    public MemberInfoResponseDto(Long userId) {
        this.id = userId;
    }
}

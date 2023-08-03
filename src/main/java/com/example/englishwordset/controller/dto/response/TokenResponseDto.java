package com.example.englishwordset.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TokenResponseDto {

    private final String accessToken;

    private final String refreshToken;
}

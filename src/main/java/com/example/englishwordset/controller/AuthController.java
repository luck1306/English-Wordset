package com.example.englishwordset.controller;

import com.example.englishwordset.controller.dto.response.TokenResponseDto;
import com.example.englishwordset.service.AuthService;
import com.example.englishwordset.controller.dto.request.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody AuthRequestDto requestDto) {
        authService.signUp(requestDto.getId(), requestDto.getPassword());
    }

    @PostMapping("/login")
    public TokenResponseDto login(@Valid @RequestBody AuthRequestDto requestDto) {
        return authService.login(requestDto.getId(), requestDto.getPassword());
    }
}

package com.example.englishwordset.controller;

import com.example.englishwordset.controller.dto.response.TokenResponseDto;
import com.example.englishwordset.service.UserService;
import com.example.englishwordset.controller.dto.request.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody AuthRequestDto requestDto) {
        userService.signUp(requestDto.getId(), requestDto.getPassword());
    }

    @PostMapping("/login")
    public TokenResponseDto login(@Valid @RequestBody AuthRequestDto requestDto) {
        return userService.login(requestDto.getId(), requestDto.getPassword());
    }

    @PutMapping("/reissue")
    public TokenResponseDto reissue(@RequestHeader(name = "refresh_token") String token) {
        return userService.reissue(token);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logout() {
        userService.logout();
    }

    @GetMapping("/test")
    public String test() {
        return "test get result";
    }
}

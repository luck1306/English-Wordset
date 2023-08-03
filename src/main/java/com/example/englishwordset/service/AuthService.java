package com.example.englishwordset.service;

import com.example.englishwordset.controller.dto.response.TokenResponseDto;
import com.example.englishwordset.entity.user.User;
import com.example.englishwordset.entity.user.UserRepository;
import com.example.englishwordset.exceptioin.PasswdNotMatchedException;
import com.example.englishwordset.exceptioin.UserAlreadyExistException;
import com.example.englishwordset.exceptioin.UserNotFoundException;
import com.example.englishwordset.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    public void signUp(String userId, String password) {
        boolean dataInDb = userRepository.findById(userId).isPresent();
        if (dataInDb) throw UserAlreadyExistException.EXCEPTION;
        else {
            userRepository.save(User.builder()
                    .id(userId)
                    .password(passwordEncoder.encode(password))
                    .build());
        }
    }

    public TokenResponseDto login(String userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(()-> UserNotFoundException.EXCEPTION);
        if (!passwordEncoder.matches(password, user.getPassword())) throw PasswdNotMatchedException.EXCEPTION;
        return TokenResponseDto.builder()
                .accessToken(jwtProvider.accessTokenGenerator(userId))
                .refreshToken(jwtProvider.refreshTokenGenerator(userId))
                .build();
    }
}

package com.example.englishwordset.service;

import com.example.englishwordset.controller.dto.response.TokenResponseDto;
import com.example.englishwordset.entity.refresh.Refresh;
import com.example.englishwordset.entity.refresh.RefreshRepository;
import com.example.englishwordset.entity.user.User;
import com.example.englishwordset.entity.user.UserRepository;
import com.example.englishwordset.exceptioin.PasswdNotMatchedException;
import com.example.englishwordset.exceptioin.UserAlreadyExistException;
import com.example.englishwordset.exceptioin.UserNotFoundException;
import com.example.englishwordset.security.JwtProvider;
import com.example.englishwordset.security.details.Details;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final RefreshRepository refreshRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Transactional
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

    @Transactional
    public TokenResponseDto login(String userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(()-> UserNotFoundException.EXCEPTION);
        if (!passwordEncoder.matches(password, user.getPassword())) throw PasswdNotMatchedException.EXCEPTION;
        return TokenResponseDto.builder()
                .accessToken(jwtProvider.accessTokenGenerator(userId))
                .refreshToken(jwtProvider.refreshTokenGenerator(userId))
                .build();
    }

    @Transactional
    public void logout() {
        Details details = (Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Refresh refresh = refreshRepository.findById(details.getUsername()).orElseThrow(()-> UserNotFoundException.EXCEPTION);
        refreshRepository.delete(refresh);
    }
}

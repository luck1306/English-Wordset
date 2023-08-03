package com.example.englishwordset.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = parseTokenToRequest(request);
        if (token != null) {
            Claims claims = jwtProvider.parseToken(token);
            jwtProvider.tokenValid(claims.get("type", String.class), claims.getExpiration());
            Authentication authentication = jwtProvider.getAuthentication(claims.getSubject());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String parseTokenToRequest(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER);
        if (token != null && token.startsWith(JwtProperties.PREFIX)) {
            return token.replace(JwtProperties.PREFIX, "");
        }
        return null;
    }
}

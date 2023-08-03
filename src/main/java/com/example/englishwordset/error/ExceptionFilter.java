package com.example.englishwordset.error;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ProjectCustomException e) {
            response.setStatus(500);
            response.setContentType("application/json");
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getCode());
            String message = errorResponse.toString();
            response.getWriter().write(message);
        }
    }
}

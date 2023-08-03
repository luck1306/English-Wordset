package com.example.englishwordset.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final String message;
    private final Integer code;

    @Override
    public String toString() {
        return String.format("{ \"message\":\"%s\",\"status\":%d }", this.message, this.code);
    }
}

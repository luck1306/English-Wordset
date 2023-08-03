package com.example.englishwordset.error;

import lombok.Getter;

@Getter
public class ProjectCustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public ProjectCustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

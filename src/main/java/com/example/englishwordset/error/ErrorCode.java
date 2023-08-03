package com.example.englishwordset.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("user not found exception", 404),
    USER_ALREADY_EXIST("user already exist in db", 400),
    PASSWD_NOT_MATCHED("password is incorrect", 409),
    TOKEN_TYPE_WRONG("token type is wrong", 401),
    TOKEN_EXPIRED("token is expired", 401);

    private final String message;
    private final Integer code;
}

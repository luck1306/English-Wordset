package com.example.englishwordset.exceptioin;

import com.example.englishwordset.error.ErrorCode;
import com.example.englishwordset.error.ProjectCustomException;

public class UserAlreadyExistException extends ProjectCustomException {
    public static final UserAlreadyExistException EXCEPTION = new UserAlreadyExistException();
    private UserAlreadyExistException() {
        super(ErrorCode.USER_ALREADY_EXIST);
    }
}

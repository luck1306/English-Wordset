package com.example.englishwordset.exceptioin;

import com.example.englishwordset.error.ErrorCode;
import com.example.englishwordset.error.ProjectCustomException;

public class UserNotFoundException extends ProjectCustomException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();
    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}

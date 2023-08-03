package com.example.englishwordset.exceptioin;

import com.example.englishwordset.error.ErrorCode;
import com.example.englishwordset.error.ProjectCustomException;

public class TokenExpiredException extends ProjectCustomException {
    public final static TokenExpiredException EXCEPTION = new TokenExpiredException();
    private TokenExpiredException() {
        super(ErrorCode.TOKEN_EXPIRED);
    }
}

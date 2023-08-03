package com.example.englishwordset.exceptioin;

import com.example.englishwordset.error.ErrorCode;
import com.example.englishwordset.error.ProjectCustomException;

public class TokenTypeWrongException extends ProjectCustomException {
    public static final TokenTypeWrongException EXCEPTION = new TokenTypeWrongException();
    private TokenTypeWrongException() {
        super(ErrorCode.TOKEN_TYPE_WRONG);
    }
}

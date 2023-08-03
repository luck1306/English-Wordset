package com.example.englishwordset.exceptioin;

import com.example.englishwordset.error.ErrorCode;
import com.example.englishwordset.error.ProjectCustomException;

public class PasswdNotMatchedException extends ProjectCustomException {
    public static final PasswdNotMatchedException EXCEPTION = new PasswdNotMatchedException();
    private PasswdNotMatchedException() {
        super(ErrorCode.PASSWD_NOT_MATCHED);
    }
}

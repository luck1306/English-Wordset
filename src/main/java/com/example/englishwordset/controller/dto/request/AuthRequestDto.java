package com.example.englishwordset.controller.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
public class AuthRequestDto {

    @NotEmpty(message = "please init any value in id")
    @Pattern(
            regexp = "[0-9a-zA-Z]+",
            message = "id does not match requirements"
    )
    private String id;

    @NotEmpty(message = "please init any value in password")
    @Pattern(
            regexp = "[a-zA-Z0-9!#$%&'()*+,./:;\"+\"<=>?@＼^_`{|}~]{8,8}",
            message = "password should be 8 words"
    )
    private String password;;
}

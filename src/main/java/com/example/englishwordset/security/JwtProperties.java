package com.example.englishwordset.security;

import io.jsonwebtoken.io.Encoders;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.nio.charset.StandardCharsets;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String secret;

    private final Long access;

    private final Long refresh;

    public static final String accessValue = "access";

    public static final String refreshValue = "refresh";

    public static final String HEADER = "Authorization";

    public static final String PREFIX = "Bearer ";

    public JwtProperties(String secret, Long access, Long refresh) {
        this.secret = Encoders.BASE64.encode(secret.getBytes(StandardCharsets.UTF_8));
        this.access = access;
        this.refresh = refresh;
    }
}

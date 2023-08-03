package com.example.englishwordset.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private final String host;

    private final Integer port;

    public RedisProperties(String host, Integer port) {
        this.host = host;
        this.port = port;
    }
}

package com.example.englishwordset.entity.refresh;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash
@Builder
@AllArgsConstructor
public class Refresh {

    @Id
    private String userId;

    @Indexed
    private String refresh;

    @TimeToLive
    private Long ttl;

    public Refresh updateToken(String token) {
        this.refresh = token;
        return this;
    }
}

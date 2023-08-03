package com.example.englishwordset.security;

import com.example.englishwordset.entity.refresh.Refresh;
import com.example.englishwordset.entity.refresh.RefreshRepository;
import com.example.englishwordset.exceptioin.TokenExpiredException;
import com.example.englishwordset.exceptioin.TokenTypeWrongException;
import com.example.englishwordset.security.details.DetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final RefreshRepository refreshRepository;
    private final DetailsService detailsService;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] decodedSecret = Decoders.BASE64.decode(jwtProperties.getSecret());
        secretKey = Keys.hmacShaKeyFor(decodedSecret);
    }

    private String generateToken(String type, String userId, Long expired) {
        return Jwts.builder()
                .claim("type", type)
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + expired * 1000))
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .compact();
    }

    public String accessTokenGenerator(String userId) {
        return generateToken("access", userId, jwtProperties.getAccess());
    }

    public String refreshTokenGenerator(String userId) {
        String refreshToken = generateToken("refresh", userId, jwtProperties.getRefresh());
        Refresh refresh = refreshRepository.findById(userId).orElse(null);
        if (refresh != null) {
            refreshRepository.save(refresh.updateToken(refreshToken));
        } else {
            refreshRepository.save(Refresh.builder()
                    .userId(userId)
                    .refresh(refreshToken)
                    .ttl(jwtProperties.getRefresh())
                    .build());
        }
        return refreshToken;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseToken(token);
        if (claims.get("type", String.class).equals("refresh")) throw TokenTypeWrongException.EXCEPTION;
        if (claims.get("exp", Date.class).before(new Date())) throw TokenExpiredException.EXCEPTION;

        UserDetails principal = detailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }

    private Claims parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }
}

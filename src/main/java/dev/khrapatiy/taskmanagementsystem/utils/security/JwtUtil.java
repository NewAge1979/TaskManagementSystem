package dev.khrapatiy.taskmanagementsystem.utils.security;

import dev.khrapatiy.taskmanagementsystem.config.JwtConfig;
import dev.khrapatiy.taskmanagementsystem.dto.response.TokensResponse;
import dev.khrapatiy.taskmanagementsystem.enums.TokenType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private static final String BEARER_PREFIX = "Bearer ";
    private final Map<String, TokensResponse> tokens = new ConcurrentHashMap<>();

    public TokensResponse createTokens(UserDetails userDetails) {
        log.info("Creating new tokens for {}", userDetails.getUsername());
        removeTokens(userDetails);
        String accessTokenSecret = jwtConfig.getAccessTokenSecret();
        Long accessTokenLifeTime = jwtConfig.getAccessTokenLifetime();
        String accessToken = createToken(userDetails, accessTokenSecret, accessTokenLifeTime);
        String refreshTokenSecret = jwtConfig.getRefreshTokenSecret();
        Long refreshTokenLifeTime = jwtConfig.getRefreshTokenLifetime();
        String refreshToken = createToken(userDetails, refreshTokenSecret, refreshTokenLifeTime);
        TokensResponse tokensResponse = new TokensResponse(accessToken, refreshToken);
        tokens.put(userDetails.getUsername(), tokensResponse);
        return tokensResponse;
    }

    public void removeTokens(UserDetails userDetails) {
        tokens.remove(userDetails.getUsername());
    }

    public boolean validateToken(TokenType tokenType, String token, UserDetails userDetails) {
        return true;
    }

    public String getEmailFromToken(TokenType tokenType, String token) {
        return "";
    }

    private String createToken(UserDetails userDetails, String secret, Long lifeTime) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        return Jwts.builder().claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + lifeTime))
                .signWith(getSecretKey(secret))
                .compact();
    }

    private SecretKey getSecretKey(String secret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
package dev.khrapatiy.taskmanagementsystem.utils.security;

import dev.khrapatiy.taskmanagementsystem.config.JwtConfig;
import dev.khrapatiy.taskmanagementsystem.dto.response.TokensResponse;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.enums.TokenType;
import dev.khrapatiy.taskmanagementsystem.exception.TokenException;
import io.jsonwebtoken.*;
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
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {
    private final JwtConfig jwtConfig;
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

    public String getEmailFromToken(TokenType tokenType, String token) throws TokenException {
        String secret = tokenType.equals(TokenType.ACCESS_TOKEN) ? jwtConfig.getAccessTokenSecret() : jwtConfig.getRefreshTokenSecret();
        try {
            String email = getClaim(token, secret, Claims::getSubject);
            if (!tokens.containsKey(email)) {
                throw new TokenException("Недопустимый JWT токен.");
            }
            return getClaim(token, secret, Claims::getSubject);
        } catch (ExpiredJwtException e) {
            throw new TokenException("Просроченный или недействительный JWT токен.");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("Неподдерживаемый JWT токен.");
        } catch (MalformedJwtException e) {
            throw new TokenException("Искаженный JWT токен.");
        } catch (Exception e) {
            throw new TokenException("Недопустимый JWT токен.");
        }
    }

    private String createToken(UserDetails userDetails, String secret, Long lifeTime) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUser) {
            claims.put("id", customUser.getId());
            claims.put("sub", customUser.getEmail());
        }
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

    private Claims getClaimsFromToken(String token, String secret) {
        return Jwts.parser()
                .verifyWith(getSecretKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T getClaim(String token, String secret, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(getClaimsFromToken(token, secret));
    }
}
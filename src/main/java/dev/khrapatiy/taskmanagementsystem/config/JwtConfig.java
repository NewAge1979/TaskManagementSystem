package dev.khrapatiy.taskmanagementsystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
    private String accessTokenSecret;
    private Long accessTokenLifetime;
    private String refreshTokenSecret;
    private Long refreshTokenLifetime;
}
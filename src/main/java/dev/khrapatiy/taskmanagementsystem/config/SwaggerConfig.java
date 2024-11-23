package dev.khrapatiy.taskmanagementsystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Task Management System",
                description = "Api for Task Management System",
                version = "1.0.0",
                contact = @Contact(
                        name = "Max Khrapatiy",
                        email = "max.khrapatiy@gmail.com",
                        url = "https://khrapatiy.dev"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Адрес для LocalHost"),
                @Server(url = "http://localhost:8888", description = "Адрес для Docker"),
                @Server(url = "https://khrapatiy.dev", description = "khrapatiy.dev")
        }
)

@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
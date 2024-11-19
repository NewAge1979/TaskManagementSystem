package dev.khrapatiy.taskmanagementsystem.utils.security;

import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.enums.TokenType;
import dev.khrapatiy.taskmanagementsystem.exception.TokenException;
import dev.khrapatiy.taskmanagementsystem.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtUtil jwtUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isNotEmpty(authHeader) && StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            var token = authHeader.substring(BEARER_PREFIX.length());
            log.info("Authorized request!");
            log.info("JWT token found: {}", token);
            try {
                String email = jwtUtil.getEmailFromToken(TokenType.ACCESS_TOKEN, token);
                log.info("JWT email found: {}", email);
                if (StringUtils.isNotEmpty(email) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = userService.findByEmail(email);
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authentication);
                    SecurityContextHolder.setContext(context);
                }
                filterChain.doFilter(request, response);
            } catch (TokenException e) {
                log.info("JWT token could not be parsed: {}", e.getMessage());
                handlerExceptionResolver.resolveException(request, response, null, e);
            }
        } else {
            log.info("Unauthorized request!");
            filterChain.doFilter(request, response);
        }
    }
}
package dev.khrapatiy.taskmanagementsystem.controller.impl;

import dev.khrapatiy.taskmanagementsystem.controller.AuthController;
import dev.khrapatiy.taskmanagementsystem.dto.request.UserDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TokensResponse;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.service.AuthService;
import dev.khrapatiy.taskmanagementsystem.service.UserService;
import dev.khrapatiy.taskmanagementsystem.utils.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserDto userDTO) {
        log.info("Sign up user: {}", userDTO.getEmail());
        authService.signUp(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PostMapping("/signIn")
    public ResponseEntity<TokensResponse> signIn(@Valid @RequestBody UserDto userDTO) {
        log.info("Sign in user: {}", userDTO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(userDTO));
    }

    @Override
    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user) {
            log.info("Sign out user: {}", user.getEmail());
            jwtUtil.removeTokens(user);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @PostMapping("/addAdmin")
    public ResponseEntity<Void> addAdmin() {
        UserDto userDto = new UserDto();
        userDto.setEmail("admin@email.com");
        userDto.setPassword("p@$$w0rd");
        authService.addAdmin(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
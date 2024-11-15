package dev.khrapatiy.taskmanagementsystem.controller.impl;

import dev.khrapatiy.taskmanagementsystem.controller.AuthController;
import dev.khrapatiy.taskmanagementsystem.dto.request.UserDTO;
import dev.khrapatiy.taskmanagementsystem.dto.response.TokensResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/auth")
@Log4j2
public class AuthControllerImpl implements AuthController {
    @Override
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserDTO userDTO) {
        log.info("Sign up user: {}", userDTO.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PostMapping("/signIn")
    public ResponseEntity<TokensResponse> signIn(@Valid @RequestBody UserDTO userDTO) {
        log.info("Sign in user: {}", userDTO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(new TokensResponse("access", "refresh"));
    }
}
package dev.khrapatiy.taskmanagementsystem.controller.impl;

import dev.khrapatiy.taskmanagementsystem.controller.AuthController;
import dev.khrapatiy.taskmanagementsystem.dto.request.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/auth")
public class AuthControllerImpl implements AuthController {
    @Override
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

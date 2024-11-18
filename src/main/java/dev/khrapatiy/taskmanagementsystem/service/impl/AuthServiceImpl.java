package dev.khrapatiy.taskmanagementsystem.service.impl;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TokensResponse;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.exception.UserException;
import dev.khrapatiy.taskmanagementsystem.mapper.UserMapper;
import dev.khrapatiy.taskmanagementsystem.repository.UserRepository;
import dev.khrapatiy.taskmanagementsystem.service.AuthService;
import dev.khrapatiy.taskmanagementsystem.service.UserService;
import dev.khrapatiy.taskmanagementsystem.utils.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void signUp(UserDto userDto) {
        if(!userExists(userDto.getEmail())) {
            User user = userMapper.toEntity(userDto);
            userRepository.save(user);
        } else {
            throw new UserException("Пользователь " + userDto.getEmail() + " уже существует.");
        }
    }

    @Override
    public TokensResponse signIn(UserDto userDto) {
        if(userExists(userDto.getEmail())) {
            try {
                UserDetails user = userService.loadUserByUsername(userDto.getEmail());
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
                );
                TokensResponse tokensResponse = jwtUtil.createTokens(user);
                log.info("Access token: {}", tokensResponse.accessToken());
                log.info("Refresh token: {}", tokensResponse.refreshToken());
                return tokensResponse;
            } catch (BadCredentialsException | UsernameNotFoundException e) {
                throw new UserException("Пользователь " + userDto.getEmail() + " не найден.");
            }
        } else {
            throw new UserException("Пользователь " + userDto.getEmail() + " не найден.");
        }
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
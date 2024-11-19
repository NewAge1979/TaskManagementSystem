package dev.khrapatiy.taskmanagementsystem.service;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TokensResponse;

public interface AuthService {
    void signUp(UserDto userDto);

    TokensResponse signIn(UserDto userDto);

    void addAdmin(UserDto userDto);
}
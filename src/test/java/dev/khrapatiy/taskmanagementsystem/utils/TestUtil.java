package dev.khrapatiy.taskmanagementsystem.utils;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDto;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.enums.Role;

public class TestUtil {
    public static User getValidUser() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPasswordHash("drowssap");
        user.setRole(Role.USER);
        return user;
    }

    public static UserDto getValidUserDTO() {
        UserDto user = new UserDto();
        user.setEmail("test@test.com");
        user.setPassword("password");
        return user;
    }
}
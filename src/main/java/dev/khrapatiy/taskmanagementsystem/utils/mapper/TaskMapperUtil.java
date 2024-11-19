package dev.khrapatiy.taskmanagementsystem.utils.mapper;

import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.exception.UserNotFoundException;
import dev.khrapatiy.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Named("TaskMapperUtil")
@RequiredArgsConstructor
public class TaskMapperUtil {
    private final UserRepository userRepository;

    @Named("getUser")
    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Named("getUserById")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь с id  = " + id + " не найден."));
    }
}
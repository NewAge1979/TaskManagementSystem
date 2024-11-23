package dev.khrapatiy.taskmanagementsystem.utils.security;

import dev.khrapatiy.taskmanagementsystem.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("CheckUserService")
public class CheckUserService {
    public boolean checkUser(Long userId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getId().equals(userId);
    }
}
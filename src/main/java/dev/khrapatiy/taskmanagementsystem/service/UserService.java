package dev.khrapatiy.taskmanagementsystem.service;

import dev.khrapatiy.taskmanagementsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    User findByEmail(String email) throws UsernameNotFoundException;
}
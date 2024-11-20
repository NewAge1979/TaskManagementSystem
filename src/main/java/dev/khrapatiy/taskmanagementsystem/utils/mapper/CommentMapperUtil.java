package dev.khrapatiy.taskmanagementsystem.utils.mapper;

import dev.khrapatiy.taskmanagementsystem.entity.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Named("CommentMapperUtil")
@RequiredArgsConstructor
public class CommentMapperUtil {
    @Named("getUserComment")
    public static User getUserComment() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
package dev.khrapatiy.taskmanagementsystem.mapper;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDto;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.utils.mapper.UserMapperUtil;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapperUtil.class})
public interface UserMapper {
    @Mapping(target = "passwordHash", qualifiedByName = {"UserMapperUtil", "getPasswordHash"}, source = "password")
    @Mapping(target = "role", constant = "USER")
    User toEntity(UserDto userDTO);

    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "passwordHash", qualifiedByName = {"UserMapperUtil", "getPasswordHash"}, source = "password")
    User partialUpdate(UserDto userDTO, @MappingTarget User user);
}
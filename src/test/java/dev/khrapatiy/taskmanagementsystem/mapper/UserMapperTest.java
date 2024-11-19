package dev.khrapatiy.taskmanagementsystem.mapper;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDto;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.enums.Role;
import dev.khrapatiy.taskmanagementsystem.utils.TestUtil;
import dev.khrapatiy.taskmanagementsystem.utils.mapper.UserMapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserMapperUtil userMapperUtil;

    @Test
    void toEntity_NoNull() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        User user = userMapper.toEntity(userDTO);
        assertNotNull(user);
    }

    @Test
    void toEntity_PasswordIsHashed() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        User user = userMapper.toEntity(userDTO);
        assertNotEquals(userDTO.getPassword(), user.getPasswordHash());
    }

    @Test
    void toEntity_DefaulRoleIsAssigned() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        User user = userMapper.toEntity(userDTO);
        assertEquals(Role.ROLE_USER, user.getRole());
    }

    @Test
    void toDto_NotNull() {
        User user = TestUtil.getValidUser();
        UserDto userDTO = userMapper.toDto(user);
        assertNotNull(userDTO);
    }

    @Test
    void partialUpdate_NotNull() {
        User user = TestUtil.getValidUser();
        UserDto userDTO = TestUtil.getValidUserDTO();
        user = userMapper.partialUpdate(userDTO, user);
        assertNotNull(user);
    }

    @Test
    void partialUpdate_PasswordIsHashed() {
        User user = TestUtil.getValidUser();
        UserDto userDTO = TestUtil.getValidUserDTO();
        user = userMapper.partialUpdate(userDTO, user);
        assertNotEquals(userDTO.getPassword(), user.getPasswordHash());
    }
}
package dev.khrapatiy.taskmanagementsystem.controller;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDto;
import dev.khrapatiy.taskmanagementsystem.entity.User;
import dev.khrapatiy.taskmanagementsystem.enums.Role;
import dev.khrapatiy.taskmanagementsystem.repository.UserRepository;
import dev.khrapatiy.taskmanagementsystem.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthControllerTest {
    public static final String API_1_0_AUTH_SIGN_UP = "/api/1.0/auth/signUp";
    public static final String API_1_0_AUTH_SIGN_IN = "/api/1.0/auth/signIn";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    // Тестирование регистрации.
    @Test
    public void postSignUp_whenUserIsValid_receive201() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    public void postSignUp_whenUserIsValid_userSaved() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, userDTO, Object.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void postSignUp_whenUserIsValid_passwordIsHashed() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, userDTO, Object.class);
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        user.ifPresent(value -> assertThat(value.getPasswordHash()).isNotEqualTo(userDTO.getPassword()));
    }

    @Test
    public void postSignUp_whenUserIsValid_defaultRoleIsAssigned() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, userDTO, Object.class);
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        user.ifPresent(value -> assertThat(value.getRole()).isEqualTo(Role.USER));
    }

    @Test
    public void postSignUp_whenUserEmailIsNotValid_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        userDTO.setEmail("test@test");
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignUp_whenUserEmailIsNull_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        userDTO.setEmail(null);
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignUp_whenUserPasswordIsNotValid_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        userDTO.setPassword("pass");
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignUp_whenUserPasswordIsNull_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        userDTO.setPassword(null);
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    // Тестирование авторизации.
    @Test
    public void postSignIn_whenUserIsValid_receive200() {
        userRepository.save(TestUtil.getValidUser());
        UserDto userDTO = TestUtil.getValidUserDTO();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void postSignIn_whenUserNotExists_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignIn_whenUserEmailIsNotValid_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        userDTO.setEmail("test@test");
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignIn_whenUserEmailIsNull_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        userDTO.setEmail(null);
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignIn_whenUserPasswordIsNotValid_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        userDTO.setPassword("pass");
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignIn_whenUserPasswordIsNull_receive400() {
        UserDto userDTO = TestUtil.getValidUserDTO();
        userDTO.setPassword(null);
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, userDTO, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }
}
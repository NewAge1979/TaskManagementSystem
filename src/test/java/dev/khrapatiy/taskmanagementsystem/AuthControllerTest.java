package dev.khrapatiy.taskmanagementsystem;

import dev.khrapatiy.taskmanagementsystem.dto.request.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthControllerTest {
    public static final String API_1_0_AUTH_SIGN_UP = "/api/1.0/auth/signUp";
    public static final String API_1_0_AUTH_SIGN_IN = "/api/1.0/auth/signIn";

    @Autowired
    private TestRestTemplate restTemplate;

    // Тестирование регистрации.
    @Test
    public void postSignUp_whenUserIsValid_receive201() {
        UserDTO user = getValidUser();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    public void postSignUp_whenUserEmailIsNotValid_receive400() {
        UserDTO user = getValidUser();
        user.setEmail("test@test");
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignUp_whenUserEmailIsNull_receive400() {
        UserDTO user = getValidUser();
        user.setEmail(null);
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignUp_whenUserPasswordIsNotValid_receive400() {
        UserDTO user = getValidUser();
        user.setPassword("pass");
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignUp_whenUserPasswordIsNull_receive400() {
        UserDTO user = getValidUser();
        user.setPassword(null);
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    // Тестирование авторизации.
    @Test
    public void postSignIn_whenUserIsValid_receive200() {
        UserDTO user = getValidUser();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void postSignIn_whenUserEmailIsNotValid_receive400() {
        UserDTO user = getValidUser();
        user.setEmail("test@test");
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignIn_whenUserEmailIsNull_receive400() {
        UserDTO user = getValidUser();
        user.setEmail(null);
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignIn_whenUserPasswordIsNotValid_receive400() {
        UserDTO user = getValidUser();
        user.setPassword("pass");
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void postSignIn_whenUserPasswordIsNull_receive400() {
        UserDTO user = getValidUser();
        user.setPassword(null);
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_IN, user, Object.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    private static UserDTO getValidUser() {
        UserDTO user = new UserDTO();
        user.setEmail("test@test.com");
        user.setPassword("password");
        return user;
    }
}
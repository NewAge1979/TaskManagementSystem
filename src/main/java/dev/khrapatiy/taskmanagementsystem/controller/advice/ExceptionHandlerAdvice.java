package dev.khrapatiy.taskmanagementsystem.controller.advice;

import dev.khrapatiy.taskmanagementsystem.dto.response.ApiErrorDetails;
import dev.khrapatiy.taskmanagementsystem.exception.TaskNotFoundException;
import dev.khrapatiy.taskmanagementsystem.exception.TokenException;
import dev.khrapatiy.taskmanagementsystem.exception.UserExistsException;
import dev.khrapatiy.taskmanagementsystem.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("MethodArgumentNotValidException");
        final List<Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(new ValidateErrorResponse(violations));
    }*/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        log.info("MethodArgumentNotValidException");
        final List<ApiErrorDetails> errors = new ArrayList<>();
        for (final ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(
                    ApiErrorDetails.builder()
                            .pointer(((FieldError) error).getField())
                            .reason(error.getDefaultMessage())
                            .build()
            );
        }
        return ResponseEntity.status(BAD_REQUEST).body(buildProblemDetail(BAD_REQUEST, "Ошибка валидации", errors));
    }

    /*@Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("HandlerMethodValidationException");
        final List<Violation> violations = new ArrayList<>();
        for (final var validation : ex.getAllValidationResults()) {
            final String parameterName = validation.getMethodParameter().getParameterName();
            validation.getResolvableErrors().forEach(error -> {
                violations.add(new Violation(parameterName, error.getDefaultMessage()));
            });
        }
        return ResponseEntity.badRequest().body(new ValidateErrorResponse(violations));
    }*/

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("HandlerMethodValidationException");
        final List<ApiErrorDetails> errors = new ArrayList<>();
        for (final var validation : ex.getAllValidationResults()) {
            final String parameterName = validation.getMethodParameter().getParameterName();
            validation.getResolvableErrors().forEach(error -> {
                errors.add(
                        ApiErrorDetails.builder()
                                .pointer(parameterName)
                                .reason(error.getDefaultMessage())
                                .build()
                );
            });
        }
        return ResponseEntity.status(BAD_REQUEST).body(buildProblemDetail(BAD_REQUEST, "Ошибка валидации", errors));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("HttpMessageNotReadableException");
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> userExistsException(UserExistsException e) {
        log.info("UserExistsException");
        final List<ApiErrorDetails> errors = new ArrayList<>();
        errors.add(
                ApiErrorDetails.builder()
                        .pointer("Users")
                        .reason(e.getMessage())
                        .build()
        );
        return ResponseEntity.status(BAD_REQUEST).body(buildProblemDetail(BAD_REQUEST, "Ошибка регистрации", errors));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException e) {
        log.info("UserNotFoundException");
        final List<ApiErrorDetails> errors = new ArrayList<>();
        errors.add(
                ApiErrorDetails.builder()
                        .pointer("Users")
                        .reason(e.getMessage())
                        .build()
        );
        return ResponseEntity.status(NOT_FOUND).body(buildProblemDetail(NOT_FOUND, "Ошибка поиск пользователя в БД", errors));
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Object> tokenException(TokenException e) {
        log.info("TokenException");
        final List<ApiErrorDetails> errors = new ArrayList<>();
        errors.add(
                ApiErrorDetails.builder()
                        .pointer("JWT Tokens")
                        .reason(e.getMessage())
                        .build()
        );
        return ResponseEntity.status(UNAUTHORIZED).body(buildProblemDetail(UNAUTHORIZED, "Ошибка валидации токена", errors));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> taskException(TaskNotFoundException e) {
        log.info("TaskNotFoundException");
        final List<ApiErrorDetails> errors = new ArrayList<>();
        errors.add(
                ApiErrorDetails.builder()
                        .pointer("Tasks")
                        .reason(e.getMessage())
                        .build()
        );
        return ResponseEntity.status(NOT_FOUND).body(buildProblemDetail(NOT_FOUND, "Ошибка поиска задачи в БД", errors));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> accessDeniedException(AccessDeniedException e) {
        log.info("AccessDeniedException");
        final List<ApiErrorDetails> errors = new ArrayList<>();
        errors.add(
                ApiErrorDetails.builder()
                        .pointer("Access")
                        .reason("Доступ запрещен.")
                        .build()
        );
        return ResponseEntity.status(FORBIDDEN).body(buildProblemDetail(FORBIDDEN, "Ошибка доступа", errors));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeException(RuntimeException e) {
        log.info("RuntimeException");
        final List<ApiErrorDetails> errors = new ArrayList<>();
        errors.add(
                ApiErrorDetails.builder()
                        .pointer("Server Error")
                        .reason(e.getMessage())
                        .build()
        );
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(buildProblemDetail(INTERNAL_SERVER_ERROR, "Ошибка сервера", errors));
    }

    private ProblemDetail buildProblemDetail(
            final HttpStatus status,
            final String detail,
            final List<ApiErrorDetails> errors
    ) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, StringUtils.normalizeSpace(detail));
        if (CollectionUtils.isNotEmpty(errors)) {
            problemDetail.setProperty("errors", errors);
            problemDetail.setProperty("timestamp", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now()));
        }
        return problemDetail;
    }
}
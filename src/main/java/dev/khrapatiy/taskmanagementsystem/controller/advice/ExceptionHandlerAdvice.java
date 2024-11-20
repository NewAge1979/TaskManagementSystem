package dev.khrapatiy.taskmanagementsystem.controller.advice;

import dev.khrapatiy.taskmanagementsystem.dto.Violation;
import dev.khrapatiy.taskmanagementsystem.dto.response.ApiErrorDetails;
import dev.khrapatiy.taskmanagementsystem.dto.response.ErrorResponse;
import dev.khrapatiy.taskmanagementsystem.dto.response.ValidateErrorResponse;
import dev.khrapatiy.taskmanagementsystem.exception.TaskNotFoundException;
import dev.khrapatiy.taskmanagementsystem.exception.TokenException;
import dev.khrapatiy.taskmanagementsystem.exception.UserExistsException;
import dev.khrapatiy.taskmanagementsystem.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("MethodArgumentNotValidException");
        final List<Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(new ValidateErrorResponse(violations));
    }

    /*@Override
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
        return ResponseEntity.badRequest().body(buildProblemDetail(BAD_REQUEST, "Validation failed", errors));
    }*/

    @Override
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
    }

    /*@Override
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
        return ResponseEntity.badRequest().body(buildProblemDetail(BAD_REQUEST, "Validation failed", errors));
    }*/

    /*@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }*/

    /*@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.info("HttpMessageNotReadableException");
        return ResponseEntity.badRequest().body(new ErrorResponse(
                StringUtils.startsWith(e.getMessage(), "Required request body is missing") ? "Пустое тело запроса" : e.getMessage()
        ));
    }*/

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorResponse> userExistsException(UserExistsException e) {
        log.info("UserExistsException");
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException e) {
        log.info("UserNotFoundException");
        return ResponseEntity.status(404).body(new ErrorResponse(e.getClass().getSimpleName()));
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ErrorResponse> tokenException(TokenException e) {
        log.info("TokenException");
        return ResponseEntity.status(401).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskException(TaskNotFoundException e) {
        log.info("TaskNotFoundException");
        return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException e) {
        log.info("AccessDeniedException");
        return ResponseEntity.status(403).body(new ErrorResponse("Доступ запрещен."));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeException(RuntimeException e) {
        log.info("RuntimeException");
        log.error(e.getMessage(), e);
        e.printStackTrace(System.err);
        return ResponseEntity.status(500).body(new ErrorResponse(e.getMessage()));
    }

    private ProblemDetail buildProblemDetail(
            final HttpStatus status,
            final String detail,
            final List<ApiErrorDetails> errors
    ) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, StringUtils.normalizeSpace(detail));
        if (CollectionUtils.isNotEmpty(errors)) {
            problemDetail.setProperty("errors", errors);
        }
        return problemDetail;
    }
}
package com.yurisilva.mundialechallenge.handler;

import com.yurisilva.mundialechallenge.exception.AuthenticationErrorDetails;
import com.yurisilva.mundialechallenge.exception.ErrorDetails;
import com.yurisilva.mundialechallenge.exception.BusinessErrorDetails;
import com.yurisilva.mundialechallenge.exception.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleAuthenticationCredentialsNotFoundException(ResponseStatusException responseStatusException) {

        BusinessErrorDetails businessErrorDetails = BusinessErrorDetails.Builder
                .aBusinessErrorDetails()
                .withTimestamp(new Date().getTime())
                .withStatus(responseStatusException.getStatus().value())
                .withTitle("Business Error")
                .withDetails(responseStatusException.getReason())
                .withDeveloperMessage(responseStatusException.getClass().getName())
                .build();

        return new ResponseEntity<>(businessErrorDetails, responseStatusException.getStatus());
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<Object> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException authenticationCredentialsNotFoundException) {

        AuthenticationErrorDetails authenticationErrorDetails = AuthenticationErrorDetails.Builder
                .anAuthenticationErrorDetails()
                .withTimestamp(new Date().getTime())
                .withStatus(HttpStatus.UNAUTHORIZED.value())
                .withTitle("Authentication Error")
                .withDetails(authenticationCredentialsNotFoundException.getMessage())
                .withDeveloperMessage(authenticationCredentialsNotFoundException.getClass().getName())
                .build();

        return new ResponseEntity<>(authenticationErrorDetails, HttpStatus.UNAUTHORIZED);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationErrorDetails validationErrorDetails = ValidationErrorDetails.Builder
                .aValidationErrorDetails()
                .withTimestamp(new Date().getTime())
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withTitle("Field Validation Error")
                .withDetails("Field Validation Error")
                .withDeveloperMessage(ex.getClass().getName())
                .withField(fields)
                .withFieldMessage(fieldMessages)
                .build();

        return new ResponseEntity<>(validationErrorDetails, headers, status);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.Builder
                .anErrorDetails()
                .withTimestamp(new Date().getTime())
                .withStatus(status.value())
                .withTitle("Internal Exception")
                .withDetails(ex.getMessage())
                .withDeveloperMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, headers, status);
    }
}

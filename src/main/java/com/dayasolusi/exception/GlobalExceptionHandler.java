package com.dayasolusi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    String logMessage = "Error with REQUEST ID: {} {}";
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        UUID requestId = UUID.randomUUID();
        LOGGER.error(logMessage, requestId, ex.getMessage());

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));
        ErrorResponse<Map<String, String>> response = new ErrorResponse<>("Validation data failed!", errors, requestId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex){
        UUID requestId = UUID.randomUUID();
        LOGGER.error(logMessage, requestId, ex.getMessage());

        ErrorResponse<String> response = new ErrorResponse<>("Data not found!", ex.getMessage(), requestId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        UUID requestId = UUID.randomUUID();
        LOGGER.error(logMessage, requestId, ex.getMessage());

        ErrorResponse<String> response = new ErrorResponse<>("Something went wrong, please try again later!", null, requestId);
        return ResponseEntity.internalServerError().body(response);
    }
}

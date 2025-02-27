package com.dayasolusi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ErrorResponse<T> {
    private String message;
    private T errors;

    @JsonProperty("request_id")
    private UUID requestId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(String message, T errors){
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, T errors, UUID requestId){
        this.message = message;
        this.errors = errors;
        this.requestId = requestId;
        this.timestamp = LocalDateTime.now();
    }
}

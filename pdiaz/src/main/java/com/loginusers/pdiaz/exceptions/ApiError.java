package com.loginusers.pdiaz.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError extends Exception{
    private final ApiTypeError type;
    private LocalDateTime timestamp;
    private String detail;
    private final HttpStatus code;

    public ApiError(ApiTypeError type, LocalDateTime timestamp, String detail, HttpStatus code) {
        this.type = type;
        this.timestamp = timestamp;
        this.detail = detail;
        this.code = code;
    }
}

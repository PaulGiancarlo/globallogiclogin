package com.loginusers.pdiaz.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    private LocalDateTime timestamp;
    private int codigo;
    private String detail;

    public ApiError(LocalDateTime timestamp, int codigo, String detail) {
        this.timestamp = timestamp;
        this.codigo = codigo;
        this.detail = detail;
    }
}

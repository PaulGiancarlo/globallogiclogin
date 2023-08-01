package com.loginusers.pdiaz.exceptions;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class InvalidInputException extends RuntimeException {

    private List<ApiError> errors;

    public InvalidInputException(String message) {
        super(message);
        this.errors = Collections.singletonList(new ApiError(LocalDateTime.now(), 400, message));
    }

    public List<ApiError> getErrors() {
        return errors;
    }
}

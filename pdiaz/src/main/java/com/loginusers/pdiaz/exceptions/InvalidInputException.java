package com.loginusers.pdiaz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@EnableWebMvc
@RestControllerAdvice
public class InvalidInputException {

    @ExceptionHandler(ApiError.class)
    public ResponseEntity<ApiResponseError> handleException(ApiError ex, WebRequest webRequest) {
        String tempName = ex.getType().name();
        UUID uuid = UUID.randomUUID();
        ApiResponseError response = new ApiResponseError("UserAPI", uuid.toString(), ex.getType(),
                ex.getCode().value(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(response,ex.getCode());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiResponseError> handleHttpClientErrorException(HttpClientErrorException ex,
                                                                           WebRequest webRequest) {
        ApiResponseError response = errorRestTemplateBody(ex, webRequest);
        return new ResponseEntity<>(response, ex.getStatusCode());

    }

    private ApiResponseError errorRestTemplateBody(RestClientResponseException ex, WebRequest webRequest) {
        ApiResponseError response;
        response = new ApiResponseError("UserAPI", UUID.randomUUID().toString(),ApiTypeError.BUISINESS,ex.getRawStatusCode(),ex.getMessage(),webRequest.getDescription(false));

    return response;

    }

}

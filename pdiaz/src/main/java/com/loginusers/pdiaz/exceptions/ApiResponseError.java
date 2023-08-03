package com.loginusers.pdiaz.exceptions;

import lombok.Getter;

@Getter
public class ApiResponseError {
    private String uuid;
    private ApiTypeError type;
    private int code;
    private String message;
    private String module;
    private String uri;

    public ApiResponseError(String module, String uuid, ApiTypeError type, int code, String message, String uri) {
        super();
        this.uuid = uuid == null ? "" : uuid;
        this.type = type == null ? ApiTypeError.EXCEPTION : type;
        this.code = code <= 0 ? 500 : code;
        this.message = message == null ? "" : message.trim();
        this.module = module == null ? "" : module;
        this.uri = uri == null ? "" : uri.replace("uri=/", "");
    }
}

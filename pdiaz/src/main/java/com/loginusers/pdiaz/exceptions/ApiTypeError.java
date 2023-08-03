package com.loginusers.pdiaz.exceptions;

import org.springframework.lang.Nullable;

public enum ApiTypeError {
    BUISINESS(1,"Error de negocio"),
    EXCEPTION(2,"Error no controlado"),
    TIMEOUT(3,"Time Out");

    private static final ApiTypeError[] VALUES;

    static {
        VALUES = values();
    }

    private final int value;

    private final String reasonPhrase;

    ApiTypeError(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override
    public String toString() {
        return this.value + " " + name();
    }


    public static ApiTypeError valueOf(int statusCode) {
        ApiTypeError status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        }
        return status;
    }

    @Nullable
    public static ApiTypeError resolve(int statusCode) {
        // Use cached VALUES instead of values() to prevent array allocation.
        for (ApiTypeError status : VALUES) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}

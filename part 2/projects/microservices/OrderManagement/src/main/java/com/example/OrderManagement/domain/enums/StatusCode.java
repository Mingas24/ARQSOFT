package com.example.OrderManagement.domain.enums;

public enum StatusCode {
    OK(200),
    CREATED(201),
    UPDATED(204),
    DELETED(204),
    BAD_REQUEST(400),
    ACCESS_DENIED(403),
    NOT_FOUND(404);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

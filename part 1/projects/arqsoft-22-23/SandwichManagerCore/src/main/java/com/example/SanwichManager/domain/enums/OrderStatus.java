package com.example.SanwichManager.domain.enums;

public enum OrderStatus {

    PROCESSED("Processed"),
    CONCLUDED("Concluded"),
    CANCELLED("Cancelled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

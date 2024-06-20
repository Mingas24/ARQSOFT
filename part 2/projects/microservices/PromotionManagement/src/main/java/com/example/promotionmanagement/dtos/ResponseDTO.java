package com.example.promotionmanagement.dtos;

public class ResponseDTO {

    public int statusCode;
    public Object object;

    public ResponseDTO(int statusCode, Object object) {
        this.statusCode = statusCode;
        this.object = object;
    }

    public ResponseDTO() {
    }
}

package com.example.SanwichManager.dtos.result;

public class ResponseDTO {

    public int statusCode;
    public Object object;

    public ResponseDTO(int statusCode, Object object) {
        this.statusCode = statusCode;
        this.object = object;
    }
}

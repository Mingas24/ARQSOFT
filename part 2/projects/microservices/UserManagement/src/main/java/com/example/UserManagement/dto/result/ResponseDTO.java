package com.example.UserManagement.dto.result;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {

    public int statusCode;
    public Object object;
}

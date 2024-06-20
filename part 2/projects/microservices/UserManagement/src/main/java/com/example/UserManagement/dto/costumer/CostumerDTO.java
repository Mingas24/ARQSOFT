package com.example.UserManagement.dto.costumer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostumerDTO {
    private String name;

    private String email;

    private String username;

    private String password;

    private String role;

    private String costumerNIF;

    private String address;
}

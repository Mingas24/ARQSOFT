package com.example.UserManagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String name;

    private String email;

    private String username;

    private String password;

    private String role;
}

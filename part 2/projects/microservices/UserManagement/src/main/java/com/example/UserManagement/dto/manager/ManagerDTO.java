package com.example.UserManagement.dto.manager;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerDTO {

    private String name;

    private String email;

    private String username;

    private String password;

    private String role;

    public Long shopId;
}

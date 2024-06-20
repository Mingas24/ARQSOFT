package com.example.UserManagement.security.jwt;


import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest implements Serializable{

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
}

package com.example.UserManagement.security.jwt;

import com.example.UserManagement.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;


@Getter
@Setter
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final UserDTO user;

    public AuthResponse(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }
}

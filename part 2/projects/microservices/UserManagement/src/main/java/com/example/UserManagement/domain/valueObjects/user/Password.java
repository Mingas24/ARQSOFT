package com.example.UserManagement.domain.valueObjects.user;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Password {

    private String password;
}

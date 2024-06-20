package com.example.UserManagement.domain.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "auth_role", schema = "authdb")
public class Role {

    @Id
    @Column(name = "C_PK", nullable = false)
    private Long id;

    @Column(name = "C_DESCRIPTION", nullable = false)
    private String description;
}

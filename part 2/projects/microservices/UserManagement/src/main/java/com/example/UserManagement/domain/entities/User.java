package com.example.UserManagement.domain.entities;

import com.example.UserManagement.domain.valueObjects.user.Email;
import com.example.UserManagement.domain.valueObjects.user.Name;
import com.example.UserManagement.domain.valueObjects.user.Password;
import com.example.UserManagement.domain.valueObjects.user.Username;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private Name name;

    @Column(name = "email")
    private Email email;

    @Column(name = "username")
    private Username username;

    @Column(name = "password", nullable = false)
    private Password password;

    @OneToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    public User(Name name, Email email, Username username, Password password, Role role) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

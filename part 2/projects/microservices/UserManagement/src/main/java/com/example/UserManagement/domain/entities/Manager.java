package com.example.UserManagement.domain.entities;

import com.example.UserManagement.domain.valueObjects.manager.ShopId;
import com.example.UserManagement.domain.valueObjects.user.Email;
import com.example.UserManagement.domain.valueObjects.user.Name;
import com.example.UserManagement.domain.valueObjects.user.Password;
import com.example.UserManagement.domain.valueObjects.user.Username;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "manager", schema = "userdb")
public class Manager extends User{

    @Column(name="shopId", nullable = false)
    private ShopId shopId;

    @Builder
    public Manager(Name name, Email email, Username username, Password password, Role role, ShopId shopId) {
        super(name, email, username, password, role);
        this.shopId = shopId;
    }
}

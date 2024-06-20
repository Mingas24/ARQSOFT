package com.example.UserManagement.domain.entities;

import com.example.UserManagement.domain.valueObjects.costumer.Address;
import com.example.UserManagement.domain.valueObjects.costumer.CostumerNIF;
import com.example.UserManagement.domain.valueObjects.user.Email;
import com.example.UserManagement.domain.valueObjects.user.Name;
import com.example.UserManagement.domain.valueObjects.user.Password;
import com.example.UserManagement.domain.valueObjects.user.Username;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "costumer", schema = "userdb")
public class Costumer extends User{

    @Column(name = "nif", nullable = false)
    private CostumerNIF costumerNIF;

    @Column(name = "address", nullable = false)
    private Address costumerAddress;

    @Builder
    public Costumer(Name name, Email email, Username username, Password password, Role role, CostumerNIF costumerNIF, Address costumerAddress) {
        super(name, email, username, password, role);
        this.costumerNIF = costumerNIF;
        this.costumerAddress = costumerAddress;
    }
}

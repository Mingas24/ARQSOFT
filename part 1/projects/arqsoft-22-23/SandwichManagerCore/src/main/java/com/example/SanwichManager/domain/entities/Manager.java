package com.example.SanwichManager.domain.entities;

import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.domain.valueObjects.shared.Name;

import javax.persistence.*;

@Entity
@Table(name = "manager", schema = "arqsoftdb")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long managerId;

    @Column(name="name", nullable = false)
    private Name name;

    @Column(name="email", nullable = false)
    private Email email;

    @OneToOne
    private Shop shop;

    protected Manager() {}

    public Manager(Name name, Email email, Shop shop){
        this.name = name;
        this.email = email;
        this.shop = shop;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public Name getName() {
        return name;
    }

    private void setName(Name name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    private void setEmail(Email email) {
        this.email = email;
    }

    public Shop getShop() {
        return shop;
    }

    private void setShop(Shop shop) {
        this.shop = shop;
    }
}
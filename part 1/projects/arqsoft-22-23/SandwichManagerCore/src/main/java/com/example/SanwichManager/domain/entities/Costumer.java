package com.example.SanwichManager.domain.entities;

import com.example.SanwichManager.domain.valueObjects.CostumerNIF;
import com.example.SanwichManager.domain.valueObjects.shared.Address;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.domain.valueObjects.shared.Name;

import javax.persistence.*;

@Entity
@Table(name = "costumer", schema = "arqsoftdb")
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private Name costumerName;

    @Column(name = "email", nullable = false)
    private Email costumerEmail;

    @Column(name = "nif", nullable = false)
    private CostumerNIF costumerNIF;

    @Column(name = "address", nullable = false)
    private Address costumerAddress;

    protected Costumer() {
    }

    public Costumer(Name costumerName, Email costumerEmail, CostumerNIF costumerNIF, Address costumerAddress) {
        this.costumerName = costumerName;
        this.costumerEmail = costumerEmail;
        this.costumerNIF = costumerNIF;
        this.costumerAddress = costumerAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getCostumerName() {
        return costumerName;
    }

    private void setCostumerName(Name costumerName) {
        this.costumerName = costumerName;
    }

    public CostumerNIF getCostumerNIF() {
        return costumerNIF;
    }

    private void setCostumerNIF(CostumerNIF costumerNIF) {
        this.costumerNIF = costumerNIF;
    }

    public Email getCostumerEmail() {
        return costumerEmail;
    }

    private void setCostumerEmail(Email costumerEmail) {
        this.costumerEmail = costumerEmail;
    }

    public Address getCostumerAddress() {
        return costumerAddress;
    }

    private void setCostumerAddress(Address costumerAddress) {
        this.costumerAddress = costumerAddress;
    }
}

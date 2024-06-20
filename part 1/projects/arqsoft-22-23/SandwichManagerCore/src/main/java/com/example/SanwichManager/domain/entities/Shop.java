package com.example.SanwichManager.domain.entities;

import java.util.List;
import javax.persistence.*;

import com.example.SanwichManager.domain.enums.PromotionApplication;
import com.example.SanwichManager.domain.valueObjects.shared.Address;
import com.example.SanwichManager.domain.valueObjects.shared.Designation;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shop", schema = "arqsoftdb")
public class Shop {

    @Id
    @GeneratedValue
    private long shop_id;

    @Column(name = "Shop Designation")
    private Designation shopDesignation;

    @Column(name = "Shop Email")
    private Email shopEmail;

    @Column(name = "Shop Address")
    private Address shopAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    @JsonIgnore
    private List<ShopSchedule> shopSchedule;

    private PromotionApplication promotionApplication;

    public Shop() {
    }

    public Shop(Designation shopDesignation, Email shopEmail, Address shopAddress, List<ShopSchedule> shopSchedule) {
        this.shopDesignation = shopDesignation;
        this.shopEmail = shopEmail;
        this.shopAddress = shopAddress;
        this.shopSchedule = shopSchedule;
        this.promotionApplication = PromotionApplication.MostFavourable;
    }

    public long getShopId() {
        return shop_id;
    }

    public Designation getDesignation() {
        return this.shopDesignation;
    }

    public void setDesignation(Designation designation) {
        this.shopDesignation = designation;
    }

    public Email getEmail() {
        return this.shopEmail;
    }

    public void setEmail(Email email) {
        this.shopEmail = email;
    }

    public Address getAddress() {
        return this.shopAddress;
    }

    public void setAddress(Address address) {
        this.shopAddress = address;
    }

    public List<ShopSchedule> getShopSchedule() {
        return this.shopSchedule;
    }

    public void setShopSchedule(List<ShopSchedule> shopSchedule) {
        this.shopSchedule = shopSchedule;
    }

    public PromotionApplication getPromotionApplication() {
        return promotionApplication;
    }

    public void setPromotionApplication(PromotionApplication promotionApplication) {
        this.promotionApplication = promotionApplication;
    }
}

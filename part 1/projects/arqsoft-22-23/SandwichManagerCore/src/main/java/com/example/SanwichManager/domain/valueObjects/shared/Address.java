package com.example.SanwichManager.domain.valueObjects.shared;

import com.example.SanwichManager.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    private String address;

    protected Address() {
    }

    public Address(String address) {
       setAddress(address);
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        Assert.isTrue(address.length() >= Constants.MIN_CHARACTERRS_ADDRESS, String.format("Address should have more than %s characters.", Constants.MIN_CHARACTERRS_ADDRESS));
        this.address = Objects.requireNonNull(address);
    }
}

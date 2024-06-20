package com.example.OrderManagement.domain.valueObjects;

import com.example.OrderManagement.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;

@Embeddable
public class Price {

    private String price;

    public Price(String price) {
        setPrice(price);
    }

    protected Price() {
    }

    public String getPrice() {
        return price;
    }

    private void setPrice(String price) {
        Assert.isTrue(price.matches(Constants.PRICE_REGEX), "Price is incorrect");
        this.price = price;
    }
}

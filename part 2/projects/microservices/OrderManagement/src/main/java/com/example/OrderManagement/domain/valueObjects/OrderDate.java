package com.example.OrderManagement.domain.valueObjects;

import com.example.OrderManagement.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;

@Embeddable
public class OrderDate {

    private String orderDate;

    protected OrderDate(){
    }

    public OrderDate(String orderDate) {
        setOrderDate(orderDate);
    }

    public String getOrderDate() {
        return orderDate;
    }

    private void setOrderDate(String orderDate) {
        Assert.isTrue(orderDate.matches(Constants.DATE_REGEX), "Order date is not correct.");
        this.orderDate = orderDate;
    }
}

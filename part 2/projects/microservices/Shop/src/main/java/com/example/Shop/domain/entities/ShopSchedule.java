package com.example.Shop.domain.entities;

import com.example.Shop.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "shop_schedule", schema = "shopdb")
public class ShopSchedule {

    @Id
    @GeneratedValue
    private long scheduleId;
    private String day;
    private String date;

    public ShopSchedule(String day, String date) {
        setShopSchedule(day, date);
    }

    public ShopSchedule() {

    }

    private void setShopSchedule(String day, String date) {
        Assert.isTrue(Arrays.stream(Constants.DAY_STRINGS).anyMatch(day::equals),
                day + " is not a valid day of the week");
        Assert.isTrue(date.matches(Constants.SCHEDULE_REGEX), date + " is not a valid schedule");
        this.day = Objects.requireNonNull(day);
        this.date = Objects.requireNonNull(date);
    }

    public Long getId() {
        return this.scheduleId;
    }

    public String getDay() {
        return this.day;
    }

    public String getDate() {
        return this.date;
    }

}

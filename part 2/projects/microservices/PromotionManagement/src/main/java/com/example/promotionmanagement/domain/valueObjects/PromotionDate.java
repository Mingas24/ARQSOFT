package com.example.promotionmanagement.domain.valueObjects;

import com.example.promotionmanagement.utils.Constants;

import javax.persistence.Embeddable;

@Embeddable
public class PromotionDate {

    private String startDate;
    private String endDate;

    public PromotionDate(String startDate, String endDate) {
        setStartDate(startDate);
        setEndDate(endDate);

        dateVerification();
    }

    protected PromotionDate() {

    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    private void setStartDate(String startDate) {
        if(!startDate.matches(Constants.DATE_REGEX))
            throw new IllegalArgumentException("Start date invalid");
        this.startDate = startDate;
    }

    private void setEndDate(String endDate) {
        if(!endDate.matches(Constants.DATE_REGEX))
            throw new IllegalArgumentException("End date invalid");
        this.endDate = endDate;
    }

    private void dateVerification(){
        if(startDate.compareTo(endDate) > 0)
            throw new IllegalArgumentException("Start date cannot be later than ending date");
    }
}

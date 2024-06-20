package com.example.SanwichManager.domain.valueObjects;

import com.example.SanwichManager.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CostumerNIF {

    private String costumerNIF;

    protected CostumerNIF() {
    }

    public CostumerNIF(String costumerNIF) {
        setCostumerNIF(costumerNIF);
    }

    public String getCostumerNIF() {
        return costumerNIF;
    }

    private void setCostumerNIF(String costumerNIF) {
        Assert.isTrue(costumerNIF.matches(Constants.NIF_REGEX), "NIF should only have 9 numbers.");
        this.costumerNIF = Objects.requireNonNull(costumerNIF);
    }
}
package com.example.SanwichManager.domain.valueObjects.shared;

import com.example.SanwichManager.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Designation {

    private String designation;

    public Designation(String designation) {
        setDesignation(designation);
    }

    protected Designation() {
    }

    public String getDesignation()  {
        return designation;
    }

    private void setDesignation(String designation) {
        Assert.isTrue(designation.matches(Constants.DEFAULT_INPUT_REGEX), "Designation should only have alphanumeric characters");
        Assert.isTrue(designation.length() >= Constants.MIN_CHARACTERS_DESIGNATION, String.format("Designation should have more than %s characters.", Constants.MIN_CHARACTERS_DESIGNATION));
        this.designation = Objects.requireNonNull(designation);
    }
}


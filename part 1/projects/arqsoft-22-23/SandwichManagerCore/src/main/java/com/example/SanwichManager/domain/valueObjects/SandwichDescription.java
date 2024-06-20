package com.example.SanwichManager.domain.valueObjects;

import com.example.SanwichManager.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class SandwichDescription {

    private String description;

    public SandwichDescription(String description) {
        setDescription(description);
    }

    protected SandwichDescription() {

    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        Assert.isTrue(description.matches(Constants.DEFAULT_INPUT_REGEX), "Description should only have alphanumeric characters");
        Assert.isTrue(description.length() >= Constants.MIN_CHARACTERS_DESCRIPTION, String.format("Description should have more than %s characters.", Constants.MIN_CHARACTERS_DESCRIPTION));
        this.description = Objects.requireNonNull(description);

    }
}


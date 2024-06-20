package com.example.SanwichManager.domain.valueObjects.shared;

import com.example.SanwichManager.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {

    private String name;

    protected Name() {
    }

    public Name(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        Assert.isTrue(name.matches(Constants.NAME_REGEX), "Name should have the first letter capitalized and should only include characters.");
        this.name = Objects.requireNonNull(name);
    }
}

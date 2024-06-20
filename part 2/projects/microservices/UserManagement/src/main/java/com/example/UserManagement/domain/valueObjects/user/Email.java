package com.example.UserManagement.domain.valueObjects.user;

import com.example.UserManagement.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Email {

    private String email;

    protected Email() {
    }

    public Email(String email) {
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        Assert.isTrue(email.matches(Constants.EMAIL_REGEX), "Email is incorrect.");
        this.email = Objects.requireNonNull(email);
    }
}
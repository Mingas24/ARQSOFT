package com.example.UserManagement.domain.valueObjects.user;

import com.example.UserManagement.utils.Constants;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Username {

    private String username;

    protected Username() {
    }

    public Username(String username) {
        setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        Assert.isTrue(username.matches(Constants.USERNAME_REGEX), "Username should only include characters and numbers.");
        this.username = Objects.requireNonNull(username);
    }
}

package org.openchat.domain.users;

import java.util.Objects;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class User {
    private final String userId;
    private final String username;
    private final String password;
    private final String about;

    public User(String userId, String username, String password, String about) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.about = about;
    }

    public String id() {
        return userId;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String about() {
        return about;
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

}

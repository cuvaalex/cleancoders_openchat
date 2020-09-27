package org.openchat.domain.users;

import java.util.Objects;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class UserCredentials {
    private final String username;

    private final String password;
    public UserCredentials(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    public boolean matches(User user) {
        return (user.username().equals(username) && user.password().equals(password));
    }
}

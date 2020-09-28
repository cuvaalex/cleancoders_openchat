package org.openchat.domain.users;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    public String userId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return EqualsBuilder
                .reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder
                .reflectionHashCode(this);
    }
}


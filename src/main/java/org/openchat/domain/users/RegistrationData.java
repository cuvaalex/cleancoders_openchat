package org.openchat.domain.users;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class RegistrationData {
    private String username;
    private String password;
    private String about;

    public RegistrationData(String username, String password, String about) {
        this.username = username;
        this.password = password;
        this.about = about;
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
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }
}

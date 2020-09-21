package org.openchat.domain.users;

import java.util.Objects;

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

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(about, user.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, about);
    }
}

package org.openchat.domain.users;

public class RegistrationData {
    private String username;
    private String password;
    private String about;

    public RegistrationData(String username, String password, String about) {
        this.username = username;
        this.password = password;
        this.about = about;
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
}

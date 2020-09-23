package org.openchat.domain.users;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    List<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    public boolean isUsernameTaken(String username) {
        return users
                .stream().filter(user -> user.getUsername().equalsIgnoreCase(username))
                .count() > 0;
    }
}

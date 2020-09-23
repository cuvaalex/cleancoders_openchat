package org.openchat;

import org.openchat.api.UserAPI;
import org.openchat.api.UserService;
import org.openchat.domain.users.IdGenerator;
import org.openchat.domain.users.UserRepository;

import static spark.Spark.*;

public class Routes {

    private UserAPI userAPI;

    public void create() {
        createAPIs();
        swaggerRoutes();
        openchatRoutes();
    }

    private void createAPIs() {
        IdGenerator idGenerator = new IdGenerator();
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(idGenerator, userRepository);

        userAPI = new UserAPI(userService);
    }

    private void openchatRoutes() {
        get("status", (req, res) -> "OpenChat: OK!");
        post("users", (req, res) -> userAPI.createUser(req, res));
    }

    private void swaggerRoutes() {
        options("login", (req, res) -> "OK");
        options("users/:userId/timeline", (req, res) -> "OK");
        options("followings", (req, res) -> "OK");
        options("followings/:userId/followees", (req, res) -> "OK");
        options("users/:userId/wall", (req, res) -> "OK");
    }
}

package org.openchat;

import org.openchat.api.*;
import org.openchat.domain.post.Clock;
import org.openchat.domain.post.LanguageService;
import org.openchat.domain.post.PostRepository;
import org.openchat.domain.post.PostService;
import org.openchat.domain.users.IdGenerator;
import org.openchat.domain.users.UserRepository;
import org.openchat.domain.users.UserService;

import static spark.Spark.*;

public class Routes {

    private UserAPI userAPI;
    private LoginAPI loginAPI;
    private PostAPI postAPI;

    public void create() {
        createAPIs();
        swaggerRoutes();
        openchatRoutes();
    }

    private void createAPIs() {
        IdGenerator idGenerator = new IdGenerator();
        Clock clock = new Clock();

        UserRepository userRepository = new UserRepository();
        PostRepository postRepository = new PostRepository();

        LanguageService languageService = new LanguageService();
        UserService userService = new UserService(idGenerator, userRepository);
        PostService postService = new PostService(languageService, idGenerator, clock, postRepository);

        userAPI = new UserAPI(userService);
        loginAPI = new LoginAPI(userRepository);
        postAPI = new PostAPI(postService);
    }

    private void openchatRoutes() {
        get("status", (req, res) -> "OpenChat: OK!");
        post("users", (req, res) -> userAPI.createUser(req, res));
        post("login", (req, res) -> loginAPI.login(req, res));
        post("users/:userId/timeline", postAPI::createPost);

    }

    private void swaggerRoutes() {
        options("login", (req, res) -> "OK");
        options("users/:userId/timeline", (req, res) -> "OK");
        options("followings", (req, res) -> "OK");
        options("followings/:userId/followees", (req, res) -> "OK");
        options("users/:userId/wall", (req, res) -> "OK");
    }
}

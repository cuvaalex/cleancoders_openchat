package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.users.RegistrationData;
import org.openchat.domain.users.User;
import org.openchat.infrastructure.json.UserJson;
import spark.Request;
import spark.Response;

import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.openchat.infrastructure.json.UserJson.*;

public class UserAPI {
    private UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    public String createUser(Request request, Response response) {
        RegistrationData registration = registrationFrom(request);

        User user = userService.createUser(registration);
        response.status(CREATED_201);
        response.type("application/json");
        return jsonFor(user);
    }

    private RegistrationData registrationFrom(Request request) {
        JsonObject json = Json.parse(request.body()).asObject();
        return new RegistrationData(
                json.getString("username", ""),
                json.getString("password", ""),
                json.getString("about", "")
        );
    }

}

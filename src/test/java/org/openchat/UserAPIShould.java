package org.openchat;

import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openchat.api.UserAPI;
import org.openchat.api.UserService;
import org.openchat.domain.users.RegistrationData;
import org.openchat.domain.users.User;
import org.openchat.domain.users.UserNameAlreadyExistingException;
import spark.Request;
import spark.Response;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.openchat.infrastructure.builders.UserBuilder.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAPIShould {

    private static final String USER_ID = UUID.randomUUID().toString();
    private static final String USERNAME = "alice";
    private static final String PASSWORD = "ffgggh";
    private static final String ABOUT = "About Alice";
    private static final RegistrationData REGISTRATION_DATA = new RegistrationData(USERNAME, PASSWORD, ABOUT);
    private static final User USER = aUser()
            .withId(USER_ID)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withAbout(ABOUT).build();

    @Mock
    Request request;
    @Mock
    Response response;
    @Mock
    UserService userService;

    private UserAPI userAPI;

    @Before
    public void before() {
        userAPI = new UserAPI(userService);
        given(request.body()).willReturn(JsonContaining(REGISTRATION_DATA));
        given(userService.createUser(REGISTRATION_DATA)).willReturn(USER);
    }

    @Test
    public void
    return_json_representing_newly_user() {
        String result = userAPI.createUser(request, response);

        verify(response).type("application/json");
        verify(response).status(201);
        assertThat(result).isEqualTo(JsonContaining(USER));
    }

    @Test public void
    create_a_new_user() {

        userAPI.createUser(request, response);
        verify(userService).createUser(REGISTRATION_DATA);
    }

//    @Test
//    public void
//    return_an_error_when_creating_a_user_with_an_existing_username() {
//        given(userService.createUser(REGISTRATION_DATA)).willThrow(UserNameAlreadyExistingException.class);
//
//        String result = userAPI.createUser(request, response);
//
//        verify(response).status(400);
//        assertThat(result).isEqualTo("Username already in use.");
//    }
    private String JsonContaining(User user) {
        return new JsonObject()
                .add("id", user.getUserId())
                .add("username", user.getUsername())
                .add("about", user.getAbout())
                .toString();
    }

    private String JsonContaining(RegistrationData registrationData) {
        return new JsonObject()
                .add("username", registrationData.username())
                .add("password", registrationData.password())
                .add("about", registrationData.about())
                .toString();
    }
}

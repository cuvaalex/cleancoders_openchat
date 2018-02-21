package org.openchat.usecases;

import org.junit.Before;
import org.junit.Test;
import org.openchat.entities.User;
import org.openchat.repositories.InMemoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTest {
  private CreateUser useCase;
  private CreateUserRequest request;
  private User user;

  @Before
  public void setUp() throws Exception {
    Context.repository = new InMemoryRepository();
    useCase = new CreateUser();
    request = new CreateUserRequest();
    request.username = "username";
    request.password = "password";
    request.about = "about";
    user = useCase.createUser(request);
  }

  @Test
  public void userFieldsAreAllCorrect() throws Exception {
    assertThat(user.username).isEqualTo("username");
    assertThat(user.password).isEqualTo("password");
    assertThat(user.about).isEqualTo("about");
  }

  @Test
  public void createdUserIsRegistered() throws Exception {
    User u = Context.repository.getUser("username");
    assertThat(u).isEqualTo(user);

  }
}

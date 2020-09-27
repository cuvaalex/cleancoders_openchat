package org.openchat.domain.users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openchat.infrastructure.builders.UserBuilder.aUser;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryShould {

    public static final User ALICE = aUser().withUsername("Alice").build();
    public static final User JOE = aUser().withUsername("Joe").build();
    private static final UserCredentials ALICE_CREDENTIALS = new UserCredentials(ALICE.username(), ALICE.password());
    private static final UserCredentials JOE_CREDENTIALS = new UserCredentials(JOE.username(), JOE.password());
    private static final UserCredentials UNKNOWN_CREDENTIALS = new UserCredentials("unknows", "unknown");
    private UserRepository userRepository;

    @Before
    public void before() {
        userRepository = new UserRepository();
    }
    
    @Test
    public void inform_when_a_username_is_already_taken() {
        userRepository.add(ALICE);

        assertThat(userRepository.isUsernameTaken(ALICE.username())).isTrue();
        assertThat(userRepository.isUsernameTaken(JOE.username())).isFalse();
    }

    @Test
    public void return_user_valid_credentials() {
        userRepository.add(ALICE);
        userRepository.add(JOE);

        assertThat(userRepository.userFor(ALICE_CREDENTIALS)).contains(ALICE);
        assertThat(userRepository.userFor(JOE_CREDENTIALS)).contains(JOE);
        assertThat(userRepository.userFor(UNKNOWN_CREDENTIALS)).isEmpty();
    }

}

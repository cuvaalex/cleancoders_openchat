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
    private UserRepository userRepository;

    @Before
    public void before() {
        userRepository = new UserRepository();
    }
    
    @Test
    public void imform_when_a_username_is_already_taken() {
        userRepository.add(ALICE);

        assertThat(userRepository.isUsernameTaken(ALICE.getUsername())).isTrue();
        assertThat(userRepository.isUsernameTaken(JOE.getUsername())).isFalse();
    }
}

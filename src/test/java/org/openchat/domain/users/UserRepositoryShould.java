package org.openchat.domain.users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryShould {

    private UserRepository userRepository;

    @Before
    public void before() {
        userRepository = new UserRepository();
    }
    
    @Test
    public void imform_when_a_username_is_already_taken() {
        assertThat(userRepository.isUsernameTaken("Alice")).isTrue();
    }
}

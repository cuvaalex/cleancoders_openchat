package org.openchat.api;

import org.openchat.domain.users.*;

public class UserService {
    private final IdGenerator idGenerator;
    private final UserRepository userRepository;

    public UserService(IdGenerator idGenerator, UserRepository userRepository) {

        this.idGenerator = idGenerator;
        this.userRepository = userRepository;
    }

    public User createUser(RegistrationData registrationData) throws UserNameAlreadyExistingException {
        throw new UnsupportedOperationException();
    }
}

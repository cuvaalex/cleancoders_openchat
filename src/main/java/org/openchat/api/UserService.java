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
        User user = createUserFrom(registrationData);
        userRepository.add(user);
        return user;
    }

    private User createUserFrom(RegistrationData registrationData) {
        User user = new User(
                idGenerator.next(),
                registrationData.username(),
                registrationData.password(),
                registrationData.about());
        return user;
    }
}

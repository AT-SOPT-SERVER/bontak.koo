package org.sopt.domain.user.service;

import org.sopt.domain.user.domain.User;
import org.sopt.domain.user.dto.req.UserRequest;
import org.sopt.domain.user.dto.res.UserResponse;
import org.sopt.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = new User(userRequest.name());
        userRepository.save(user);

        return UserResponse.from(user);
    }
}

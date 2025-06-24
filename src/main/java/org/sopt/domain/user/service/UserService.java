package org.sopt.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.user.domain.User;
import org.sopt.domain.user.dto.req.UserRequest;
import org.sopt.domain.user.dto.res.UserResponse;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.BusinessException;
import org.sopt.global.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        User user = new User(userRequest.name());
        userRepository.save(user);

        return UserResponse.from(user);
    }

    public User findUser(Long userid) {
        return userRepository.findById(userid).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_USER));
    }
}

package org.sopt.domain.user.dto.res;

import org.sopt.domain.user.entity.User;

public record UserResponse(
        User user
) {
    public static UserResponse from(User user) {
        return new UserResponse(user);
    }
}

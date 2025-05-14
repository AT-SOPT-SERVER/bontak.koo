package org.sopt.domain.user.controller;

import org.apache.coyote.Response;
import org.sopt.domain.user.dto.req.UserRequest;
import org.sopt.domain.user.dto.res.UserResponse;
import org.sopt.domain.user.service.UserService;
import org.sopt.global.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<Void>> createUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);

        return ResponseEntity.ok(ApiResponse.created(null));
    }
}

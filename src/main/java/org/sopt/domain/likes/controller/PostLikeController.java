package org.sopt.domain.likes.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.likes.service.PostLikeService;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.post.service.PostService;
import org.sopt.domain.user.domain.User;
import org.sopt.domain.user.service.UserService;
import org.sopt.global.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/likes")
public class PostLikeController {
    private final PostLikeService postLikeService;
    private final UserService userService;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> toggleLike(@RequestHeader Long userid, @PathVariable Long postId) {
        User user = userService.findUser(userid);
        Post post = postService.findPost(postId);
        postLikeService.toggleLike(user, post);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}

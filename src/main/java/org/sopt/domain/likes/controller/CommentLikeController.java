package org.sopt.domain.likes.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.comment.service.CommentService;
import org.sopt.domain.likes.service.CommentLikeService;
import org.sopt.domain.user.domain.User;
import org.sopt.domain.user.service.UserService;
import org.sopt.global.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/{commentId}/likes")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> toggleLike(@RequestHeader Long userId, @PathVariable Long commentId) {
        User user = userService.findUser(userId);
        Comment comment = commentService.findComment(commentId);
        commentLikeService.toggleLike(user, comment);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}

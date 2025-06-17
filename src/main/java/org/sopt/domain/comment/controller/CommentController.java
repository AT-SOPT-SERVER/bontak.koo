package org.sopt.domain.comment.controller;

import org.sopt.domain.comment.dto.req.CommentRequest;
import org.sopt.domain.comment.dto.res.CommentResponse;
import org.sopt.domain.comment.service.CommentService;
import org.sopt.global.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public ResponseEntity<ApiResponse<Void>> createComment(@RequestHeader Long userId, @RequestBody final CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.createComment(userId, commentRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(null));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<ApiResponse<Void>> updateComment(@PathVariable Long id, @RequestBody final CommentRequest commentRequest) {
        commentService.updateComment(id, commentRequest);

        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}

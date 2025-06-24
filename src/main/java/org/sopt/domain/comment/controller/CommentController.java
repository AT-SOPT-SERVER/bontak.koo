package org.sopt.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.comment.dto.req.CommentRequest;
import org.sopt.domain.comment.dto.res.CommentResponse;
import org.sopt.domain.comment.service.CommentService;
import org.sopt.global.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createComment(@RequestHeader Long userId, @RequestBody final CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.createComment(userId, commentRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateComment(@PathVariable Long id, @RequestBody final CommentRequest commentRequest) {
        commentService.updateComment(id, commentRequest);

        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}

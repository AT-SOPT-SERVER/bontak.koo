package org.sopt.domain.post.controller;

import org.sopt.domain.post.dto.res.*;
import org.sopt.global.ApiResponse;
import org.sopt.domain.post.dto.req.PostRequest;
import org.sopt.domain.post.dto.req.PostUpdateRequest;
import org.sopt.domain.post.dto.res.PostListResponse;
import org.sopt.domain.post.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestHeader Long userId, @RequestBody final PostRequest postRequest) {
        PostResponse postResponse = postService.createPost(userId, postRequest);
        URI location = URI.create("/posts/" + postResponse.id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, location.toString())
                .body(ApiResponse.created(null));
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse<PostSummaryListResponse>> getAllPosts(@RequestHeader Long userId) {
        PostSummaryListResponse postSummaryListResponse = postService.getAllPosts();

        return ResponseEntity.ok(ApiResponse.ok(postSummaryListResponse));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> getPost(@RequestHeader Long userId, @PathVariable final Long id) {
        PostDetailResponse postDetailResponse = postService.getPost(id);

        return ResponseEntity.ok(ApiResponse.ok(postDetailResponse));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePostTitle(@RequestHeader Long userId, @PathVariable Long id, @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatePostTitle(id, postUpdateRequest);

        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePostById(@RequestHeader Long userId, @PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/posts/search")
    public ResponseEntity<ApiResponse<PostListResponse>> searchPostsByKeyword(@RequestParam String keyword) {
        PostListResponse postListResponse = postService.searchPostsByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.ok(postListResponse));
    }
}
package org.sopt.controller;

import org.sopt.dto.ApiResponse;
import org.sopt.dto.req.*;
import org.sopt.dto.res.*;
import org.sopt.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody final PostRequest postRequest) {
        PostResponse postResponse = postService.createPost(postRequest);
        URI location = URI.create("/posts/" + postResponse.id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, location.toString())
                .body(ApiResponse.created(null));
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts() {
        PostListResponse postListResponse = postService.getAllPosts();

        return ResponseEntity.ok(ApiResponse.ok(postListResponse));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> getPost(@PathVariable final Long id) {
        PostDetailResponse postDetailResponse = postService.getPost(id);

        return ResponseEntity.ok(ApiResponse.ok(postDetailResponse));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePostTitle(@PathVariable Long id, @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatePostTitle(id, postUpdateRequest);

        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/posts/search")
    public ResponseEntity<ApiResponse<PostListResponse>> searchPostsByKeyword(@RequestParam String keyword) {
        PostListResponse postListResponse = postService.searchPostsByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.ok(postListResponse));
    }
}
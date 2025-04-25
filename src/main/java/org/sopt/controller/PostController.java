package org.sopt.controller;

import org.sopt.dto.ApiResponse;
import org.sopt.dto.req.PostCreateRequest;
import org.sopt.dto.res.PostCreateResponse;
import org.sopt.dto.res.PostDetailResponse;
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
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody final PostCreateRequest postCreateRequest) {
        PostCreateResponse postCreateResponse = postService.createPost(postCreateRequest);

        URI location = URI.create("/posts/" + postCreateResponse.id());
        ApiResponse<Void> body = new ApiResponse<>(201, "응답 성공", null);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, location.toString())
                .body(body);
    }

//    @GetMapping("/posts")
//    public ResponseEntity<?> getAllPosts() {
//        return ResponseEntity.ok(postService.getAllPosts());
//    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> getPost(@PathVariable final Long id) {
        PostDetailResponse PostDetailResponse = postService.getPost(id);

        ApiResponse<PostDetailResponse> body = new ApiResponse<>(200, "응답 성공", PostDetailResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }

//    @PutMapping("/posts/{id}")
//    public boolean updatePostTitle(int updateId, String newTitle) {
//        return postService.updatePostTitle(updateId, newTitle);
//    }
//
//    @DeleteMapping("/posts/{id}")
//    public boolean deletePostById(int id) {
//        return postService.deletePostById(id);
//    }
//
//    @GetMapping("/posts/search?keyword=")
//    public List<Post> searchPostsByKeyword(String keyword) {
//        return postService.searchPostsByKeyword(keyword);
//    }
}

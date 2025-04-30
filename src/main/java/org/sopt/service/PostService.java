package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.req.*;
import org.sopt.dto.res.PostDetailResponse;
import org.sopt.dto.res.PostListResponse;
import org.sopt.dto.res.PostResponse;
import org.sopt.exception.BusinessException;
import org.sopt.exception.ErrorCode;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse createPost(PostRequest postRequest) {
        validateDuplicateTitle(postRequest.title());
        validatePostCreationInterval(postRepository.findTopByOrderByCreatedAtDesc());

        Post post = new Post(postRequest.title());
        postRepository.save(post);

        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public PostListResponse getAllPosts() {
        return PostListResponse.of(postRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPost(final Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_POST));

        return PostDetailResponse.of(post.getId(), post.getTitle());
    }

    public void updatePostTitle(Long id, PostUpdateRequest postUpdateRequest) {
        validateDuplicateTitle(postUpdateRequest.title());
        Post post = postRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_POST));

        post.updateTitle(postUpdateRequest.title());
    }

    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_POST));

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public PostListResponse searchPostsByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.KEYWORD_EMPTY);
        }

        List<Post> post = postRepository.findByTitleContainingIgnoreCase(keyword);
        return PostListResponse.of(post);
    }

    public void validateDuplicateTitle(String title) {
        if (postRepository.existsByTitle(title)) {
            throw new BusinessException(ErrorCode.TITLE_DUPLICATE);
        }
    }

    public void validatePostCreationInterval(Optional<Post> lastPost) {
        lastPost.ifPresent(post -> {
            Duration duration = Duration.between(post.getCreatedAt(), LocalDateTime.now());
            if (duration.getSeconds() < 180 ) {
                throw new BusinessException(ErrorCode.TIME_LIMIT);
            }
        });
    }
}
package org.sopt.domain.post.service;

import org.sopt.domain.post.dto.res.*;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.dto.req.PostRequest;
import org.sopt.domain.post.dto.req.PostUpdateRequest;
import org.sopt.domain.user.entity.User;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.BusinessException;
import org.sopt.global.ErrorCode;
import org.sopt.domain.post.repository.PostRepository;
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
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostResponse createPost(Long userId, PostRequest postRequest) {
        validateDuplicateTitle(postRequest.title());
//        validatePostCreationInterval(postRepository.findTopByOrderByCreatedAtDesc());

        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_USER));
        Post post = new Post(user, postRequest.title(), postRequest.content());
        postRepository.save(post);

        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public PostSummaryListResponse getAllPosts() {
        List<PostSummaryResponse> summaries = postRepository.findAllSummaries();
        return PostSummaryListResponse.of(summaries);
    }


    @Transactional(readOnly = true)
    public PostDetailResponse getPost(final Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_POST));

        return PostDetailResponse.of(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId());
    }

    public void updatePostTitle(Long id, PostUpdateRequest postUpdateRequest) {
        validateDuplicateTitle(postUpdateRequest.title());
        Post post = postRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_POST));

        post.updateTitle(postUpdateRequest.title(), postUpdateRequest.content());
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
            if (duration.getSeconds() < 180) {
                throw new BusinessException(ErrorCode.TIME_LIMIT);
            }
        });
    }
}
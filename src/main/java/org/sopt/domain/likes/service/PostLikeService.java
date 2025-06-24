package org.sopt.domain.likes.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.likes.domain.PostLike;
import org.sopt.domain.likes.repository.PostLikeRepository;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    public void toggleLike(User user, Post post) {
        boolean exists = postLikeRepository.existsByUserAndPost(user, post);
        if (exists) {
            postLikeRepository.deleteByUserAndPost(user, post);
        } else {
            postLikeRepository.save(PostLike.createPostLike(user, post));
        }
    }
}

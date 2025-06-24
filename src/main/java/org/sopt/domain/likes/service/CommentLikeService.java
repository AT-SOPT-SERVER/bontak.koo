package org.sopt.domain.likes.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.likes.domain.CommentLike;
import org.sopt.domain.likes.repository.CommentLikeRepository;
import org.sopt.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;

    public void toggleLike(User user, Comment comment) {
        boolean exists = commentLikeRepository.existsByUserAndComment(user, comment);
        if (exists) {
            commentLikeRepository.deleteByUserAndComment(user, comment);
        } else {
            commentLikeRepository.save(CommentLike.createCommentLike(user, comment));
        }
    }
}

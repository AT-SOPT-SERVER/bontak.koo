package org.sopt.domain.comment.service;

import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.comment.dto.req.CommentRequest;
import org.sopt.domain.comment.dto.res.CommentResponse;
import org.sopt.domain.comment.repository.CommentRepository;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.domain.user.domain.User;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.BusinessException;
import org.sopt.global.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public CommentResponse createComment(Long userId, CommentRequest commentRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById(commentRequest.postId()).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_POST));
        Comment comment = new Comment(commentRequest.text(), user.getId(), post);
        commentRepository.save(comment);
        return CommentResponse.from(comment);
    }

    public void updateComment(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_COMMENT));
        comment.update(commentRequest.text());
    }

    public void deleteCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_COMMENT));
        commentRepository.delete(comment);
    }
}

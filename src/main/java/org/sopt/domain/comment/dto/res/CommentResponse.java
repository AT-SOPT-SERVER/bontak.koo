package org.sopt.domain.comment.dto.res;

import org.sopt.domain.comment.domain.Comment;

public record CommentResponse(Long id) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getId());
    }
}

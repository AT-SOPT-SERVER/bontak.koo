package org.sopt.domain.likes.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.user.domain.User;

@Entity
@NoArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    public static CommentLike createCommentLike(User user, Comment comment) {
        CommentLike commentLike = new CommentLike();
        commentLike.user = user;
        commentLike.comment = comment;
        comment.getLikes().add(commentLike);
        return commentLike;
    }
}

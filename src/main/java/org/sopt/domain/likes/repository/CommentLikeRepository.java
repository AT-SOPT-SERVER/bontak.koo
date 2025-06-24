package org.sopt.domain.likes.repository;

import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.likes.domain.CommentLike;
import org.sopt.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByUserAndComment(User user, Comment comment);

    void deleteByUserAndComment(User user, Comment comment);
}

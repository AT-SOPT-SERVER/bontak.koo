package org.sopt.domain.comment.repository;

import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.post.dto.res.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentDto> findByPostId(Long postId);
}

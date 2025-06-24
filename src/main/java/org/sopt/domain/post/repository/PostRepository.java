package org.sopt.domain.post.repository;

import org.sopt.domain.post.domain.Post;
import org.sopt.domain.post.dto.res.PostSummaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT new org.sopt.domain.post.dto.res.PostSummaryResponse(p.title, p.user.id) FROM Post p ORDER BY p.createdAt DESC ")
    List<PostSummaryResponse> findAllSummaries();

    List<Post> findByTitleContainingIgnoreCase(String keyword);

    Boolean existsByTitle(String title);

    Optional<Post> findTopByOrderByCreatedAtDesc();
}

package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCase(String keyword);
    Boolean existsByTitle(String title);
    Optional<Post> findTopByOrderByCreatedAtDesc();
}
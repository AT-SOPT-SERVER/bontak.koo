package org.sopt.domain.likes.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.user.domain.User;

@Entity
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public static PostLike createPostLike(User user, Post post) {
        PostLike postLike = new PostLike();
        postLike.user = user;
        postLike.post = post;
        post.getLikes().add(postLike);
        return postLike;
    }
}

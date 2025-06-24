package org.sopt.domain.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.domain.comment.validator.CommentValidator;
import org.sopt.domain.likes.domain.CommentLike;
import org.sopt.domain.post.domain.Post;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Long author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CommentLike> likes;

    public Comment(String text, Long author, Post post) {
        CommentValidator.validateText(text);

        this.text = text;
        this.author = author;
        this.post = post;
    }

    public void update(String text) {
        CommentValidator.validateText(text);

        this.text = text;
    }
}

package org.sopt.domain.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.domain.comment.domain.Comment;
import org.sopt.domain.likes.domain.PostLike;
import org.sopt.domain.post.validator.TitleValidator;
import org.sopt.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PostLike> likes;

    public Post(User user, String title, String content) {
        TitleValidator.validateTitle(title);
        TitleValidator.validateContent(content);
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public void updateTitle(String title, String content) {
        TitleValidator.validateTitle(title);
        TitleValidator.validateContent(content);
        this.title = title;
        this.content = content;
    }
}

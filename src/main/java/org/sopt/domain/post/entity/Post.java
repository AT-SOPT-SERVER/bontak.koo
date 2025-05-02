package org.sopt.domain.post.entity;

import jakarta.persistence.*;
import org.sopt.domain.post.validator.TitleValidator;
import org.sopt.domain.user.entity.User;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Post() {

    }

    public Post(User user, String title, String content) {
        TitleValidator.validateTitle(title);
        TitleValidator.validateContent(content);
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateTitle(String title, String content) {
        TitleValidator.validateTitle(title);
        TitleValidator.validateContent(content);
        this.title = title;
        this.content = content;
    }
}
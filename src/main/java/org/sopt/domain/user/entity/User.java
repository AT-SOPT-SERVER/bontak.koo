package org.sopt.domain.user.entity;

import jakarta.persistence.*;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.user.validator.UserValidator;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        this.posts.add(post);
        post.setUser(this);
    }

    public User() {

    }

    public User(String name) {
        UserValidator.nameValidate(name);
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }
}

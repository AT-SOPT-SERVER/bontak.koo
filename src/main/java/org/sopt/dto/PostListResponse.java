package org.sopt.dto;

import org.sopt.domain.Post;

import java.util.List;

public record PostListResponse(List<Post> posts) {
    public static PostListResponse of(final List<Post> posts) {
        return new PostListResponse(posts);
    }
}
package org.sopt.dto;

import org.sopt.domain.Post;

public record PostCreateResponse(Long id) {
    public static PostCreateResponse from(Post post) {
        return new PostCreateResponse(post.getId());
    }
}
package org.sopt.domain.post.dto.res;

import org.sopt.domain.post.entity.Post;

public record PostResponse(Long id) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId());
    }
}
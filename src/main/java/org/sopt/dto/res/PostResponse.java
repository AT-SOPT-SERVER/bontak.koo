package org.sopt.dto.res;

import org.sopt.domain.Post;

public record PostResponse(Long id) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId());
    }
}
package org.sopt.domain.post.dto.res;

import org.sopt.domain.post.entity.Post;

import java.util.List;

public record PostListResponse(List<Post> posts) {
    public static PostListResponse of(List<Post> posts) {
        return new PostListResponse(posts);
    }
}

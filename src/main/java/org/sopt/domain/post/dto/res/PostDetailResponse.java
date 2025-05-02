package org.sopt.domain.post.dto.res;

public record PostDetailResponse(Long id, String title, String content, Long userId) {
    public static PostDetailResponse of(final Long id, final String title, final String content, final Long userId) {
        return new PostDetailResponse(id, title, content, userId);
    }
}
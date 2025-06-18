package org.sopt.domain.post.dto.res;

import java.util.List;

public record PostDetailResponse(Long id, String title, String content, Long userId, List<CommentDto> comments) {
    public static PostDetailResponse of(final Long id, final String title, final String content, final Long userId, final List<CommentDto> comments) {
        return new PostDetailResponse(id, title, content, userId, comments);
    }
}

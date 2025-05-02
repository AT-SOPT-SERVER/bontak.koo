package org.sopt.domain.post.dto.req;

public record PostUpdateRequest(
        Long id,
        String title,
        String content
) {
}

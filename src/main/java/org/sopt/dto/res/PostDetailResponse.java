package org.sopt.dto.res;

public record PostDetailResponse(Long id, String title) {
    public static PostDetailResponse of(final Long id, final String title) {
        return new PostDetailResponse(id, title);
    }
}

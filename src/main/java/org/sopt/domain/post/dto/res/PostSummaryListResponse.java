package org.sopt.domain.post.dto.res;

import java.util.List;

public record PostSummaryListResponse(List<PostSummaryResponse> posts) {
    public static PostSummaryListResponse of(List<PostSummaryResponse> posts) {
        return new PostSummaryListResponse(posts);
    }
}
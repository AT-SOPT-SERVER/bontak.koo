package org.sopt.domain.post.validator;

import org.sopt.global.BusinessException;
import org.sopt.global.ErrorCode;

public class TitleValidator {
    public static void validateTitle(String title) {
        if (title.trim().length() > 30) {
            throw new BusinessException(ErrorCode.TITLE_LENGTH);
        }

        if (title.isBlank() || title == null) {
            throw new BusinessException(ErrorCode.TITLE_EMPTY);
        }
    }

    public static void validateContent(String content) {
        if (content.isEmpty() || content == null) {
            throw new BusinessException(ErrorCode.CONTENT_EMPTY);
        }
    }
}

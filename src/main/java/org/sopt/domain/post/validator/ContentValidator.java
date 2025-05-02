package org.sopt.domain.post.validator;

import org.sopt.global.BusinessException;
import org.sopt.global.ErrorCode;

public class ContentValidator {
    public static void validateContent(String content) {
        if (content.length() > 1000) {
            throw new BusinessException(ErrorCode.CONTENT_LENGTH);
        }
    }
}

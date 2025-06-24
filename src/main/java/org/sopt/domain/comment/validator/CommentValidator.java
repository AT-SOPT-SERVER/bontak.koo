package org.sopt.domain.comment.validator;


import org.sopt.global.BusinessException;
import org.sopt.global.ErrorCode;

public class CommentValidator {
    public static void validateText(String text) {
        if (text == null || text.isEmpty()) {
            throw new BusinessException(ErrorCode.COMMENT_EMPTY);
        } else if (text.length() > 300) {
            throw new BusinessException(ErrorCode.COMMENT_LENGTH);
        }
    }
}

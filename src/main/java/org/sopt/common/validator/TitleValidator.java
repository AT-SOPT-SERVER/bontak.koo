package org.sopt.common.validator;

import org.sopt.exception.BusinessException;
import org.sopt.exception.ErrorCode;

public class TitleValidator {
    public static void validateTitle(String title) {
        if (title.trim().length() > 30) {
            throw new BusinessException(ErrorCode.TITLE_LENGTH);
        }

        if (title.isBlank() || title == null) {
            throw new BusinessException(ErrorCode.TITLE_EMPTY);
        }
    }
}

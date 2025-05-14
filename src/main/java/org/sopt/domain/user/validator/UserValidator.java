package org.sopt.domain.user.validator;

import org.sopt.global.BusinessException;
import org.sopt.global.ErrorCode;

public class UserValidator {
    public static void nameValidate(String name) {
        if (name == null || name.isBlank()) {
            throw new BusinessException(ErrorCode.USER_NAME_EMPTY);
        }

        if (name.length() > 10) {
            throw new BusinessException(ErrorCode.NAME_LENGTH);
        }
    }
}

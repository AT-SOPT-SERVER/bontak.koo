package org.sopt.exception;

import org.sopt.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Void> response = ApiResponse.fail(errorCode.getCode(), errorCode.getMsg());

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }
}

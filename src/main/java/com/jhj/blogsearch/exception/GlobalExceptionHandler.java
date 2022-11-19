package com.jhj.blogsearch.exception;

import com.jhj.blogsearch.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 요청 파라미터가 없을 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse<Object> handleMissingParamsException(final MissingServletRequestParameterException exception) {
        log.error(exception.getMessage(), exception);
        return ApiResponse.error(ErrorCode.VALIDATION_BAD_REQUEST_EXCEPTION);
    }

    /**
     * 존재하지 않는 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ApiResponse<Object> handleNotFoundException(final NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ApiResponse.error(getValueOrDefault(exception.getErrorCode(), ErrorCode.NOT_FOUND_EXCEPTION));
    }

    /**
     * 잘못된 입력이 들어왔을 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    protected ApiResponse<Object> handleValidationException(final ValidationException exception) {
        log.error(exception.getMessage(), exception);
        return ApiResponse.error(getValueOrDefault(exception.getErrorCode(), ErrorCode.VALIDATION_EXCEPTION));
    }

    /**
     * 서버 내부에서 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiResponse<Object> handleException(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return ApiResponse.error(ErrorCode.INTERNAL_SERVER_EXCEPTION);
    }


    private static ErrorCode getValueOrDefault(ErrorCode customCode, ErrorCode defaultCode) {
        return customCode == null ? defaultCode : customCode;
    }
}

package com.jhj.blogsearch.exception;

public class ValidationException extends BlogSearchException {

    public ValidationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ValidationException(String message) {
        super(message);
    }
}

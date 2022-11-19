package com.jhj.blogsearch.exception;

public class NotFoundException extends BlogSearchException {

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message) {
        super(message);
    }
}

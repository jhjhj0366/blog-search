package com.jhj.blogsearch.exception;

import lombok.Getter;

@Getter
public class BlogSearchException extends RuntimeException {

    private ErrorCode errorCode;

    public BlogSearchException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BlogSearchException(String message) {
        super(message);
    }

}
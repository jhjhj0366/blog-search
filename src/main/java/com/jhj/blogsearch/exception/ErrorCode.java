package com.jhj.blogsearch.exception;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // Common Exception
    NOT_FOUND_EXCEPTION("C001", "Not found Data"),
    VALIDATION_EXCEPTION("C002", "Wrong request"),
    INTERNAL_SERVER_EXCEPTION("C003", "Internal Server Error"),

    // Validation Exception
    VALIDATION_BAD_REQUEST_EXCEPTION("V001", "Parameter is not available");

    private final String code;
    private final String message;

}

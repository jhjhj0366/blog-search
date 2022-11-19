package com.jhj.blogsearch.common;

import com.jhj.blogsearch.exception.ErrorCode;
import lombok.Getter;
import lombok.Builder;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiResponse<T> {

    private String code;
    private T data;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime serverDatetime;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                            .code(HttpStatus.OK.toString())
                            .data(data)
                            .message("success")
                            .serverDatetime(LocalDateTime.now())
                            .build();
    }

    public static ApiResponse<Object> error(ErrorCode errorCode) {
        return ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .serverDatetime(LocalDateTime.now()).build();
    }

    public static ApiResponse<Object> error(ErrorCode errorCode, String message) {
        return ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(message)
                        .serverDatetime(LocalDateTime.now()).build();
    }
}

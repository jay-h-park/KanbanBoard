package com.example.kanban.utils;

import org.springframework.http.HttpStatus;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(Throwable throwable, HttpStatus httpStatus) {
        return new ApiResult<>(false, null, new ApiError(throwable, httpStatus));
    }

    public static ApiResult<?> error(String  message, HttpStatus httpStatus) {
        return new ApiResult<>(false, null, new ApiError(message, httpStatus));
    }

    public static class ApiError {
        private final String message;
        private final int status;

        ApiError(String message, HttpStatus status) {
            this.message = message;
            this.status = status.value();
        }

        ApiError(Throwable throwable, HttpStatus httpStatus) {
            this(throwable.getMessage(), httpStatus);
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }
    }

    public static class ApiResult<T> {
        private final boolean success;
        private final T response;
        private final ApiError error;

        private ApiResult(boolean success, T response, ApiError error) {
            this.success = success;
            this.response = response;
            this.error = error;
        }

        public boolean isSuccess() {
            return success;
        }

        public T getResponse() {
            return response;
        }

        public ApiError getError() {
            return error;
        }
    }
}

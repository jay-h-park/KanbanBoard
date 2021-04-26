package com.example.kanban.errors;

import com.example.kanban.utils.ApiUtils;
import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(String message, HttpStatus httpStatus) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return new ResponseEntity<>(ApiUtils.error(message, httpStatus), httpHeaders, httpStatus);
    }

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(Throwable throwable, HttpStatus httpStatus) {
        return newResponse(throwable.getMessage(), httpStatus);
    }

    @ExceptionHandler({
            NotFoundException.class
    })
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return newResponse(e, HttpStatus.NOT_FOUND);
    }
}

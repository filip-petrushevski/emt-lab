package com.emt.lab.exception.handler;

import com.emt.lab.dto.ApiResponseDto;
import com.emt.lab.exception.BadRequestException;
import com.emt.lab.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponseDto> handleNotFound(NotFoundException exception) {
        System.err.println(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto(404, exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponseDto> handleBadRequest(BadRequestException exception) {
        System.err.println(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(400, exception.getMessage()));
    }
}

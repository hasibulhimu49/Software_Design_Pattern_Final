package com.PIMS.exceptions;

import com.PIMS.dto.response.ApiResponse;
import com.PIMS.dto.response.ProductCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(DuplicateSkuException.class)
    public ResponseEntity<ApiResponse<ProductCreateResponse>> duplicateSkuException(DuplicateSkuException exception){
        log.error(exception.getMessage());

        ApiResponse<ProductCreateResponse> response = new ApiResponse<>(
                HttpStatus.CONFLICT.toString(),
                409,
                exception.getMessage(),
                true,
                null

        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<ProductCreateResponse>> duplicateSkuException(ProductNotFoundException exception){
        log.error(exception.getMessage());

        ApiResponse<ProductCreateResponse> response = new ApiResponse<>(
                HttpStatus.NOT_FOUND.toString(),
                404,
                exception.getMessage(),
                true,
                null

        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}

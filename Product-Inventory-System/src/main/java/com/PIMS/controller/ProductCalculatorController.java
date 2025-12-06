package com.PIMS.controller;

import com.PIMS.dto.request.ProductCreateRequest;
import com.PIMS.dto.response.ApiResponse;
import com.PIMS.dto.response.ProductCreateResponse;
import com.PIMS.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductCalculatorController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductCreateResponse>> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        log.debug("Received request to create product");
        ApiResponse<ProductCreateResponse> response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

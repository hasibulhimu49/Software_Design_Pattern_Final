package com.PIMS.services;

import com.PIMS.dto.request.ProductCreateRequest;
import com.PIMS.dto.response.ApiResponse;
import com.PIMS.dto.response.ProductCreateResponse;
import com.PIMS.entity.Product;
import com.PIMS.exceptions.DuplicateSkuException;
import com.PIMS.mapper.ProductMapper;
import com.PIMS.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ApiResponse<ProductCreateResponse> createProduct(ProductCreateRequest request) {
        if(productRepository.existsBySku(request.getSku())){
            throw new DuplicateSkuException("Product with SKU "+request.getSku()+" already exists");
        }

        Product product = ProductMapper.toEntity(request);
        Double discountPrice = ProductCalculator.calculateDiscountPrice(product.getPrice(),product.getDiscount());
        product.setFinalPrice(discountPrice);
        productRepository.save(product);

        log.info("Product with SKU {} has been created", request.getSku());

        ProductCreateResponse response = ProductMapper.toDto(product);

        ApiResponse<ProductCreateResponse> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED.toString(),
                201,
                "Product Created Successfully",
                false,
                response
        );
        return apiResponse;

    }
}

package com.PIMS.mapper;

import com.PIMS.dto.request.ProductCreateRequest;
import com.PIMS.dto.request.UpdateProductRequest;
import com.PIMS.dto.response.ProductCreateResponse;
import com.PIMS.entity.Product;

public  class ProductMapper {

    public static ProductCreateResponse toDto(Product product){
        if(product == null){
            return null;
        }

        return ProductCreateResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .sku(product.getSku())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .finalPrice(product.getFinalPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public static Product toEntity(ProductCreateRequest request){
        if(request == null){
            return null;
        }

        return Product.builder()
                .productName(request.getProductName())
                .sku(request.getSku())
                .price(request.getPrice())
                .discount(request.getDiscount())
                .quantity(request.getQuantity())
                .build();
    }

    public static void updateEntity(Product product, UpdateProductRequest request) {
        if (request == null || product == null) {
            return;
        }

        if (request.getProductName() != null) {
            product.setProductName(request.getProductName());
        }
        if (request.getSku() != null) {
            product.setSku(request.getSku());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }
        if (request.getDiscount() != null) {
            product.setDiscount(request.getDiscount());
        }
    }
}

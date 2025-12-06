package com.PIMS.services;

import com.PIMS.dto.request.ProductCreateRequest;
import com.PIMS.dto.response.ProductCreateResponse;
import com.PIMS.entity.Product;
import com.PIMS.exceptions.ProductNotFoundException;
import com.PIMS.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductManagerService {
    private  ProductRepository productRepository;

    public  ProductCreateResponse findProductBySku(String sku) {
        Product product = productRepository.findBySku(sku).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        log.info("find product by sku : {}", product);
        return ProductCreateResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .sku(product.getSku())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .discount(product.getDiscount())
                .finalPrice(product.getFinalPrice())
                .build();
    }

    public ProductCreateResponse restockProduct(String sku,Integer addQuantity){
        Product product =  productRepository.findBySku(sku).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setQuantity(product.getQuantity()+addQuantity);
        productRepository.save(product);

        log.info("restock product : {}",product);
        return ProductCreateResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .sku(product.getSku())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .discount(product.getDiscount())
                .finalPrice(product.getFinalPrice())
                .build();
    }
}

package com.PIMS.services;

import com.PIMS.dto.response.ProductCreateResponse;
import com.PIMS.entity.Product;
import com.PIMS.exceptions.ProductNotFoundException;
import com.PIMS.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductManagerService productManagerService;

    @Test
    void testTofindProductBySku_Successfully(){
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Advanced Java Programming");
        product.setSku("ABCD-1234");
        product.setPrice(400);
        product.setDiscount(30);
        product.setQuantity(20);
        Double result = (double) (product.getPrice() - (product.getPrice() * product.getDiscount())/100);
        product.setFinalPrice(result);

        when(productRepository.findBySku("ABCD-1234")).thenReturn(Optional.of(product));

        ProductCreateResponse response = productManagerService.findProductBySku("ABCD-1234");

        assertEquals(product.getProductName(),response.getProductName());
        assertEquals(product.getSku(),response.getSku());
    }

    @Test
    void testTofindProductBySku_ProductNotFound(){
        when(productRepository.findBySku("ABCD-1234")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                ()-> productManagerService.findProductBySku("ABCD-1234"));
    }

    @Test
    void testToRestockProduct_Successfully(){
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Advanced Java Programming");
        product.setSku("ABCD-1234");
        product.setPrice(400);
        product.setDiscount(30);
        product.setQuantity(20);
        Double result = (double) (product.getPrice() - (product.getPrice() * product.getDiscount())/100);
        product.setFinalPrice(result);

        when(productRepository.findBySku("ABCD-1234")).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));


        ProductCreateResponse response = productManagerService.restockProduct("ABCD-1234",10);

        assertEquals(30,response.getQuantity());
        assertEquals(product.getSku(),response.getSku());
        verify(productRepository, times(1)).save(product);

    }

    @Test
    void testToRestockProduct_ProductNotFound(){
        when(productRepository.findBySku("ABCD-1234")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                ()-> productManagerService.restockProduct("ABCD-1234",10));
    }

}
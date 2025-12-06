package com.PIMS.services;

import com.PIMS.dto.request.ProductCreateRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCalculatorTest {

    private final ProductCalculator productCalculator = new ProductCalculator();

    @Test
    void testToCalculateProductPrice_NoDiscount(){
        Double result = productCalculator.calculateDiscountPrice(100,0);
        assertEquals(100,result);
    }

    @Test
    void testToCalculateProductPrice_HalfDiscount(){
        Double result = productCalculator.calculateDiscountPrice(100,50);
        assertEquals(50,result);
    }

    @Test
    void testToCalculateProductPrice_FullDiscount(){
        Double result = productCalculator.calculateDiscountPrice(100,100);
        assertEquals(0,result);
    }

    @Test
    void testToCalculateProductPrice_InvalidDiscount(){
        assertThrows(IllegalArgumentException.class,
                () -> productCalculator.calculateDiscountPrice(100,-10));
    }

    @Test
    void testIsQuantitySufficient_EnoughQuantity(){
        Boolean result = productCalculator.isQuantitySufficient(5,2);
        assertEquals(true,result);
    }

    @Test
    void testIsQuantitySufficient_NotEnoughQuantity(){
       assertThrows(IllegalArgumentException.class,
               () -> productCalculator.isQuantitySufficient(1,5));
    }


}
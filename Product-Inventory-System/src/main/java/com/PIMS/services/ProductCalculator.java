package com.PIMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCalculator {

    public static Double calculateDiscountPrice(Integer price, Integer discount){
        if(discount<0 || discount>100){
            throw new IllegalArgumentException("discount must be between 0 and 100");
        }
        Double finalPrice = price -  (double) ((price*discount)/100);
        return finalPrice;
    }

    public static Boolean  isQuantitySufficient(Integer quantity, Integer requiredQuantity){
        if(requiredQuantity > quantity){
            throw new IllegalArgumentException("Quantity must be greater than required quantity");
        }
        return true;
    }
}

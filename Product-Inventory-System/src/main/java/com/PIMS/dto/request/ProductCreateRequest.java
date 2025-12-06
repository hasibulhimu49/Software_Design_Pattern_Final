package com.PIMS.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {
    @NotBlank(message = "Product name cannot be empty")
    private String productName;
    @NotBlank(message = "SKU cannot be empty")
    @Column(unique = true)
    private String sku;
    @Positive(message = "Price must be greater than 0")
    private Integer price;
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;
    private Integer discount;
}

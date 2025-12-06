package com.PIMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateResponse {
    private Long id;
    private String productName;
    private String sku;
    private Integer price;
    private Integer quantity;
    private Integer discount;
    private Double finalPrice;
}

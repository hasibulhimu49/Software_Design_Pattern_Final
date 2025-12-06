package com.PIMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "producrs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Product name cannot be empty")
    @Column(name = "P_Name")
    private String productName;

    @NotBlank(message = "SKU cannot be empty")
    @Column(name = "sku", unique = true)
    private String sku;
    @Positive(message = "Price must be greater than 0")
    private Integer price;
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;

    private Integer discount;
    private Double finalPrice;
}

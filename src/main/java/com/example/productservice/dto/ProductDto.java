package com.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ProductDto {
    private Integer id;
    private String name;
    private CategoryDto category;
    private Integer categoryId;
    private Integer price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository  productRepository;

    public ProductDto get(Integer id) {
        ProductEntity productEntity = getEntity(id);
        if (productEntity != null) {
            ProductDto dto = new ProductDto();
            dto.setName(productEntity.getName());
            dto.setPrice(productEntity.getPrice());
            return dto;
        }
        return null;
    }

    public ProductDto create(ProductDto dto) {
        if (dto == null) throw new IllegalStateException("dto must not be null!");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(dto.getName());
        productEntity.setPrice(dto.getPrice());
        productEntity.setCreatedAt(LocalDateTime.now());
        productRepository.save(productEntity);
        return dto;
    }

    public boolean update(Integer id, ProductDto dto) {
        ProductEntity productEntity = getEntity(id);
        productEntity.setName(dto.getName());
        productEntity.setUpdatedAt(LocalDateTime.now());
        productRepository.save(productEntity);
        return true;
    }

    public boolean delete(Integer id) {
        Integer numberOfRowDeleted = productRepository.deleteUserAt(id);
        return numberOfRowDeleted > 0;

    }

    public ProductEntity getEntity(Integer id) {
        Optional<ProductEntity> optional = productRepository.findByIdAndDeletedAtIsNull(id);
        return optional.orElse(null);
    }
    public List<ProductEntity> getAll() {
        return productRepository.getAll();
    }
}

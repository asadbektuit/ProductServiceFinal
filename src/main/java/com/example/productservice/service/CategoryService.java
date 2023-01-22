package com.example.productservice.service;

import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.CategoryEntity;
import com.example.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDto get(Integer id) {
        CategoryEntity categoryEntity = getEntity(id);
        if (categoryEntity != null) {
            CategoryDto dto = new CategoryDto();
            dto.setName(categoryEntity.getName());
            return dto;
        }
        return null;
    }

    public CategoryDto create(CategoryDto categoryDto) {
        if(categoryDto == null) throw new IllegalStateException("dto must not be null!");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setCreatedAt(LocalDateTime.now());
        categoryRepository.save(categoryEntity);
        return categoryDto;
    }

    public boolean update(Integer id, CategoryDto categoryDto) {
        CategoryEntity categoryEntity = getEntity(id);
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(categoryEntity);
        return true;
    }

    public boolean delete(Integer id) {
     Integer numberOfRowDeleted = categoryRepository.deleteUserAt(id);
     return numberOfRowDeleted > 0;
    }

    public CategoryEntity getEntity(Integer id) {
        Optional<CategoryEntity> optional = categoryRepository.findByIdAndDeletedAtIsNull(id);
        return optional.orElse(null);
    }

    public List<CategoryEntity> getAll() {
        return categoryRepository.getAll();
    }
}

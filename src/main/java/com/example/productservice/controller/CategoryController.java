package com.example.productservice.controller;

import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.CategoryEntity;
import com.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        CategoryDto result = categoryService.get(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CategoryDto categoryDto) {
        CategoryDto result = categoryService.create(categoryDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody CategoryDto categoryDto) {
        boolean result = categoryService.update(id, categoryDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boolean result = categoryService.delete(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<CategoryEntity> categoryList = categoryService.getAll();
        return ResponseEntity.ok(categoryList);
    }
}

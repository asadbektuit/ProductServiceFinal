package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        ProductDto result = productService.get(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ProductDto productDto) {
        ProductDto result = productService.create(productDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) {
        boolean result = productService.update(id, productDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boolean result = productService.delete(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<ProductEntity> productList = productService.getAll();
        return ResponseEntity.ok(productList);
    }
}

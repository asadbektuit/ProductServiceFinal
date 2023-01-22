package com.example.productservice.controller;

import com.example.productservice.dto.UserRoleDto;
import com.example.productservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        UserRoleDto result = userRoleService.get(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserRoleDto userRoleDto) {
        UserRoleDto result = userRoleService.create(userRoleDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody UserRoleDto userRoleDto) {
        boolean result = userRoleService.update(id, userRoleDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boolean result = userRoleService.delete(id);
        return ResponseEntity.ok(result);
    }
}

package com.example.productservice.controller;

import com.example.productservice.dto.UserDto;
import com.example.productservice.entity.UserEntity;
import com.example.productservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        UserDto result = userService.get(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserDto userDto) {
        UserDto result = userService.create(userDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
        boolean result = userService.update(id, userDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boolean result = userService.delete(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<UserEntity> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }
}

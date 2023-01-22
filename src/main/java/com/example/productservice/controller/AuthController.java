package com.example.productservice.controller;


import com.example.productservice.dto.AuthDto;
import com.example.productservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AuthDto dto) {
        String result = authService.register(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDto dto) {
        AuthDto result = authService.login(dto);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/activate/{id}")
    public ResponseEntity activateUser(@PathVariable("id") Integer id){
        if(authService.activateUser(id)) return ResponseEntity.ok("user activated!!!");
        return ResponseEntity.ok("user doesn't exist!!!");
    }
}


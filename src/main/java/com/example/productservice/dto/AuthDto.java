package com.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {
    private String name;
    private String login;
    private String password;
    private String token;
}

package com.example.productservice.service;

import com.example.productservice.configuration.JwtTokenUtil;
import com.example.productservice.dto.AuthDto;
import com.example.productservice.entity.UserEntity;
import com.example.productservice.exception.UserNotFoundException;
import com.example.productservice.repository.UserRepository;
import com.example.productservice.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    public String register(AuthDto dto) {
        Optional<UserEntity> optional = userRepository.findByLoginAndDeletedAtIsNull(dto.getLogin());
        if (optional.isPresent()) {
            throw new IllegalArgumentException("User already exist!");
        }
        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(PasswordEncodeService.generateMD5(dto.getPassword()));
        user.setStatus(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUserRoleId(3);
        userRepository.save(user);

        return "Welcome!";
    }

    public boolean activateUser(Integer userID){
        Optional<UserEntity> optional = userRepository.findByIdAndDeletedAtIsNull(userID);
        if (optional.isEmpty()) {
            return false;
        }
        UserEntity user = optional.get();
        user.setStatus(true);
        userRepository.save(user);
        return true;
    }

    public AuthDto login(AuthDto dto) {
        Optional<UserEntity> optional =
                userRepository.findByLoginAndPasswordAndDeletedAtIsNullAndStatusIsTrue(dto.getLogin(), PasswordEncodeService.generateMD5(dto.getPassword()));
        if (optional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        UserEntity user = optional.get();
        String token = jwtTokenUtil.generateAccessToken(user.getId(), user.getLogin());

        dto.setToken(token);
        return dto;
    }
}

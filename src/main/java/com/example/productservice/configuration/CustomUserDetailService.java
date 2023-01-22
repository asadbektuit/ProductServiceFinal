package com.example.productservice.configuration;

import com.example.productservice.entity.UserEntity;
import com.example.productservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userRepository.findByLoginAndDeletedAtIsNull(username);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("User name invalid");
        }
        UserEntity user = optional.get();

        return new CustomUserDetail(user);
    }
}


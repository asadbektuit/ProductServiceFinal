package com.example.productservice.service;

import com.example.productservice.dto.UserDto;
import com.example.productservice.entity.UserEntity;
import com.example.productservice.entity.UserRoleEntity;
import com.example.productservice.repository.UserRepository;
import com.example.productservice.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserDto get(Integer id) {
        UserEntity user = getEntity(id);
        if(user != null) {
            UserDto dto = new UserDto();
            dto.setName(user.getName());
            dto.setLogin(user.getLogin());
            dto.setPassword(user.getPassword());
            return dto;
        }
        return null;
    }

    public UserDto create(UserDto dto) {
        if(dto == null) throw new IllegalStateException("dto must not be null!");

        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return dto;
    }

    public boolean update(Integer id, UserDto dto) {
        UserEntity user = getEntity(id);
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return true;
    }

    public boolean delete(Integer id) {
        Integer numberOfRowDeleted = userRepository.deleteUserAt(id);
        return numberOfRowDeleted > 0;
    }

    public UserEntity getEntity(Integer id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public List<UserEntity> getAll() {
        return userRepository.getAll();
    }

    public boolean setUserRole(Integer userId, Integer userRoleId){
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        Optional<UserRoleEntity> userRoleOpt = userRoleRepository.findById(userRoleId);

        if(userOpt.isPresent() && userRoleOpt.isPresent()){
            userOpt.get().setUserRole(userRoleOpt.get());
            userOpt.get().setUserRoleId(userRoleId);
            userRepository.save(userOpt.get());
            return true;
        }
        return false;
    }
}

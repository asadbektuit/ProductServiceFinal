package com.example.productservice.service;

import com.example.productservice.dto.UserRoleDto;
import com.example.productservice.entity.UserRoleEntity;
import com.example.productservice.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleDto get(Integer id) {
        UserRoleEntity userRoleEntity = getEntity(id);
        if (userRoleEntity != null) {
            UserRoleDto dto = new UserRoleDto();
            dto.setName(userRoleEntity.getName());
            return dto;
        }
        return null;
    }

    public UserRoleDto create(UserRoleDto dto) {
        if (dto == null) throw new IllegalStateException("dto must not be null!");

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setName(dto.getName());
        userRoleEntity.setCreatedAt(LocalDateTime.now());
        userRoleRepository.save(userRoleEntity);
        return dto;
    }

    public boolean update(Integer id, UserRoleDto dto) {
        UserRoleEntity userRoleEntity = getEntity(id);
        userRoleEntity.setName(dto.getName());
        userRoleEntity.setUpdatedAt(LocalDateTime.now());
        userRoleRepository.save(userRoleEntity);
        return true;
    }

    public boolean delete(Integer id) {
        Integer numberOfRowDeleted = userRoleRepository.deleteUserAt(id);
        return numberOfRowDeleted > 0;
    }

    public UserRoleEntity getEntity (Integer id){
        Optional<UserRoleEntity> optional = userRoleRepository.findByIdAndDeletedAtIsNull(id);
        return optional.orElse(null);
    }
}

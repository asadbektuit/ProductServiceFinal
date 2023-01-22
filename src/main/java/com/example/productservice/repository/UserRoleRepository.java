package com.example.productservice.repository;

import com.example.productservice.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Integer> {
    Optional<UserRoleEntity> findByIdAndDeletedAtIsNull(Integer id);

    @Modifying
    @Query(value = ("delete from tb_user_role where id = :id"), nativeQuery = true)
    Integer deleteUserAt(@Param("id") Integer id);
}

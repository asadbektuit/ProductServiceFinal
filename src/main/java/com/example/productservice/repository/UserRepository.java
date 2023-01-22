package com.example.productservice.repository;

import com.example.productservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByLoginAndDeletedAtIsNull(String username);

    Optional<UserEntity> findByIdAndDeletedAtIsNull(Integer id);


    @Query(value = ("select * from tb_user"), nativeQuery = true)
    List<UserEntity> getAll();

    @Modifying
    @Query(value = ("delete from tb_user where id = :id"), nativeQuery = true)
    Integer deleteUserAt(@Param("id") Integer id);

    Optional<UserEntity> findByLoginAndPasswordAndDeletedAtIsNullAndStatusIsTrue(String login, String generateMD5);
}
package com.example.productservice.repository;

import com.example.productservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByIdAndDeletedAtIsNull(Integer id);

    @Query(value = ("select * from tb_product"), nativeQuery = true)
    List<ProductEntity> getAll();

    @Modifying
    @Query(value = ("delete from tb_product where id = :id"), nativeQuery = true)
    Integer deleteUserAt(@Param("id") Integer id);
}

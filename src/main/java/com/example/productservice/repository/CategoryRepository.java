package com.example.productservice.repository;

import com.example.productservice.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    Optional<CategoryEntity> findByIdAndDeletedAtIsNull(Integer id);

    @Query(value = ("select * from tb_category"), nativeQuery = true)
    List<CategoryEntity> getAll();
    @Modifying
    @Query(value = ("delete from tb_category where id = :id"), nativeQuery = true)
    Integer deleteUserAt(@Param("id") Integer id);
}

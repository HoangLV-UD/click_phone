package com.example.world_phone.repo;

import com.example.world_phone.entity.CategoryEntity;
import com.example.world_phone.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    @Query("select o from ProductEntity o where o.deleteFlag = false and o.status = 'ON'")
    List<ProductEntity> findAll();

    @Query("select o from ProductEntity o where o.deleteFlag = false and o.status = '1' and o.name LIKE %:keyword%")
    List<ProductEntity> findByName(@Param("keyword") String name);

    ProductEntity findByIdAndDeleteFlagIsFalse(Long id);

    List<ProductEntity> findByCategoryAndDeleteFlagIsFalse(CategoryEntity categoryEntity);
}

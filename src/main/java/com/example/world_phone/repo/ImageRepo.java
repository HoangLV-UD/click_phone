package com.example.world_phone.repo;

import com.example.world_phone.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<ImageEntity, Long> {

    @Query("SELECT o from ImageEntity o where o.deleteFlag = false and o.productEntity.id = ?1")
    List<ImageEntity> findAll(Long id);
}

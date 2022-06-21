package com.example.world_phone.repo;

import com.example.world_phone.entity.AttributeProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeProductRepo extends JpaRepository<AttributeProductEntity, Long> {
    AttributeProductEntity findByProductId(long productId);
}

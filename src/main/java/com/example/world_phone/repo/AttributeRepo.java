package com.example.world_phone.repo;

import com.example.world_phone.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepo extends JpaRepository<AttributeEntity, Long> {


    List<AttributeEntity> findByProductId(Long id);
}

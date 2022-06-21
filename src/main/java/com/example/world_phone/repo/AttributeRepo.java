package com.example.world_phone.repo;

import com.example.world_phone.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepo extends JpaRepository<AttributeEntity, Long> {


    List<AttributeEntity> findByProductId(Long id);

    AttributeEntity findByIdAndDeleteFlagIsFalse(Long id);
}

package com.example.world_phone.repo;

import com.example.world_phone.entity.RomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RomRepo extends JpaRepository<RomEntity, Long> {

    @Query("select o from RomEntity o where o.productEntity.id = ?1 and o.status = '1'")
    List<RomEntity> findByProductEntity(Long id);

    RomEntity findByIdAndDeleteFlagIsFalse(Long id);
}

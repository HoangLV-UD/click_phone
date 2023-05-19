package com.example.world_phone.repo;

import com.example.world_phone.entity.RomValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RomValueRepo extends JpaRepository<RomValueEntity, Long> {
    List<RomValueEntity> findByDeleteFlagIsFalse();
}

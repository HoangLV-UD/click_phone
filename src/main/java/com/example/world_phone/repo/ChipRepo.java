package com.example.world_phone.repo;

import com.example.world_phone.entity.ChipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChipRepo extends JpaRepository<ChipEntity, Long> {
    List<ChipEntity> findByDeleteFlagIsFalse();
}

package com.example.world_phone.repo;

import com.example.world_phone.entity.PinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepo extends JpaRepository<PinEntity, Long> {
    List<PinEntity> findByDeleteFlagIsFalse();
}

package com.example.world_phone.repo;

import com.example.world_phone.entity.CamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CamRepo extends JpaRepository<CamEntity, Long> {
    List<CamEntity> findByDeleteFlagIsFalse();
    CamEntity findByIdAndDeleteFlagIsFalse(Long id);
}

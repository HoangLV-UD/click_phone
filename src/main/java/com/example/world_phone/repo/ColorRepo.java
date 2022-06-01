package com.example.world_phone.repo;

import com.example.world_phone.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepo extends JpaRepository<ColorEntity, Long> {

    List<ColorEntity> findByDeleteFlagIsFalse();

    ColorEntity findByIdAndDeleteFlagIsFalse(Long id);
}

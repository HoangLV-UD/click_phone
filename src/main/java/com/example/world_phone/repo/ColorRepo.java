package com.example.world_phone.repo;

import com.example.world_phone.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepo extends JpaRepository<ColorEntity, Long> {
}

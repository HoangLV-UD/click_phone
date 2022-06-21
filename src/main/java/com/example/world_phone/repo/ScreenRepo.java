package com.example.world_phone.repo;

import com.example.world_phone.entity.ScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepo extends JpaRepository<ScreenEntity, Long> {


    List<ScreenEntity> findByDeleteFlagIsFalse();
}

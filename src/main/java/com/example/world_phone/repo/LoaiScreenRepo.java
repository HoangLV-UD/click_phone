package com.example.world_phone.repo;

import com.example.world_phone.entity.LoaiOsEntity;
import com.example.world_phone.entity.LoaiScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiScreenRepo extends JpaRepository<LoaiScreenEntity,Long>{
    List<LoaiScreenEntity> findByDeleteFlagIsFalse();
}

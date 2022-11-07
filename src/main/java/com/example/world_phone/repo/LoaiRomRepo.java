package com.example.world_phone.repo;

import com.example.world_phone.entity.LoaiRomEntity;
import com.example.world_phone.entity.LoaiScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 05/10/2022
 * Project_name: com.example.world_phone.repo
 */
@Repository
public interface LoaiRomRepo extends JpaRepository<LoaiRomEntity, Long> {
    List<LoaiRomEntity> findByDeleteFlagIsFalse();
}

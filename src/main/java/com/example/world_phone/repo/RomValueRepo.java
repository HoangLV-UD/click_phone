package com.example.world_phone.repo;

import com.example.world_phone.entity.RomValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 07/07/2022
 * Project_name: com.example.world_phone.repo
 */
@Repository
public interface RomValueRepo extends JpaRepository<RomValueEntity, Long> {
    List<RomValueEntity> findByDeleteFlagIsFalse();
}

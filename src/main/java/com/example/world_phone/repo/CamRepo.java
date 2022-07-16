package com.example.world_phone.repo;

import com.example.world_phone.entity.CamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 29/06/2022
 * Project_name: com.example.world_phone.repo
 */
@Repository
public interface CamRepo extends JpaRepository<CamEntity, Long> {
    List<CamEntity> findByDeleteFlagIsFalse();
    CamEntity findByIdAndDeleteFlagIsFalse(Long id);
}

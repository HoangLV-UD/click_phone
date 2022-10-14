package com.example.world_phone.repo;

import com.example.world_phone.entity.LoaiRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author: hieu
 * @since: 05/10/2022
 * Project_name: com.example.world_phone.repo
 */
@Repository
public interface LoaiRomRepo extends JpaRepository<LoaiRomEntity, Long> {
}

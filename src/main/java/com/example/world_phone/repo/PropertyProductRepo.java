package com.example.world_phone.repo;

import com.example.world_phone.entity.ProductPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyProductRepo extends JpaRepository<ProductPropertyEntity, Long> {

    @Query("select o from ProductPropertyEntity o where o.deleteFlag = false and o.status = '1' and o.romEntity.id = ?1")
    List<ProductPropertyEntity> findByRom(Long id);
}

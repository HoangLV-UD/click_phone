package com.example.world_phone.repo;

import com.example.world_phone.entity.PromotionEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepo extends JpaRepository<PromotionEnity, String> {

    List<PromotionEnity> findAllByDeleteFlagFalseOrderByCreateDateDesc();

    PromotionEnity findByIdAndDeleteFlagFalse(Long id);

    List<PromotionEnity> findByStatusTrueAndDeleteFlagIsFalse();

    @Query("select o from PromotionEnity o where o.startDate <= CURRENT_DATE and o.endDate >= CURRENT_DATE and o.status = true")
    List<PromotionEnity> findByDay();
}

package com.example.world_phone.repo;

import com.example.world_phone.dto.respone.ThongKeDto;
import com.example.world_phone.entity.OrdersDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 04/12/2022
 * Project_name: com.example.world_phone.repo
 */
public interface ThongKeRepo extends JpaRepository<OrdersDetailEntity, Long> {
    @Query(name = "thong_ke", nativeQuery = true)
    List<ThongKeDto> findStockAkhirPerProductIn(
            @Param("month") Integer month,
            @Param("year") Integer year
    );
}


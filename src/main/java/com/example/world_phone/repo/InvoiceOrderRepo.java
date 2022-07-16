package com.example.world_phone.repo;


import com.example.world_phone.entity.InvoiceOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceOrderRepo extends JpaRepository<InvoiceOrderEntity, Long> {
    List<InvoiceOrderEntity> findByDeleteFlagIsFalse();
    InvoiceOrderEntity findByCodeOrder(String code);

    InvoiceOrderEntity findByIdAndDeleteFlagIsFalse(Long id);
    @Query("select o from InvoiceOrderEntity o where o.deleteFlag = false  and o.totalMoney > 0")
    List<InvoiceOrderEntity> findByDeleteFlagIsFalseAndTotalMoney();
}

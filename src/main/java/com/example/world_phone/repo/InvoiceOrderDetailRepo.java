package com.example.world_phone.repo;

import com.example.world_phone.entity.InvoiceOrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceOrderDetailRepo extends JpaRepository<InvoiceOrderDetailEntity, Long> {
    @Query("select o from InvoiceOrderDetailEntity o where o.invoiceOrderEntity.id = ?1 and o.deleteFlag = false ")
    List<InvoiceOrderDetailEntity> findByInvoiceOrderEntity(Long id);
}

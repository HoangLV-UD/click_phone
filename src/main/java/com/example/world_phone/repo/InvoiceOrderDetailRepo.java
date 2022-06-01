package com.example.world_phone.repo;

import com.example.world_phone.entity.InvoiceOrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceOrderDetailRepo extends JpaRepository<InvoiceOrderDetailEntity, Long> {

}

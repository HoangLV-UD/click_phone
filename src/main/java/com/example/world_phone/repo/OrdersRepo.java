package com.example.world_phone.repo;

import com.example.world_phone.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity, Long> {

    List<OrdersEntity> findByDeleteFlagIsFalseOrderByCreateDateDesc();


    OrdersEntity findByCodeOrderAndDeleteFlagIsFalse(String id);
}

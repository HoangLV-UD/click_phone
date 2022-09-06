package com.example.world_phone.repo;


import com.example.world_phone.entity.OrdersDetailEntity;
import com.example.world_phone.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 13/08/2022
 * Project_name: com.example.word_phone_web.repo
 */
public interface OrdersDetailRepo extends JpaRepository<OrdersDetailEntity, Long> {
    List<OrdersDetailEntity> findByDeleteFlagIsFalseAndOrdersEntity(OrdersEntity ordersEntity);

    OrdersDetailEntity findByDeleteFlagIsFalseAndId(Long id);

}

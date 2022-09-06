package com.example.world_phone.repo;

import com.example.world_phone.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 12/08/2022
 * Project_name: com.example.word_phone_web.repo
 */
@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity, Long> {

    List<OrdersEntity> findByDeleteFlagIsFalse();


    OrdersEntity findByCodeOrderAndDeleteFlagIsFalse(String id);
}

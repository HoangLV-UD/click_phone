package com.example.world_phone.repo;

import com.example.world_phone.dto.respone.customer.CustomerRespone;
import com.example.world_phone.entity.AttributeProductEntity;
import com.example.world_phone.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity, Integer> {

    List<CustomerEntity> findByPhoneNumber(String phoneNumer);
}

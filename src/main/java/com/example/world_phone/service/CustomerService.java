package com.example.world_phone.service;

import com.example.world_phone.dto.respone.customer.CustomerRespone;
import com.example.world_phone.dto.respone.order.OrderRespone;
import com.example.world_phone.entity.CustomerEntity;
import com.example.world_phone.service.impl.CustomerServiceImpl;

import java.util.List;

public interface CustomerService  {
    List<CustomerEntity> findByPhoneNumber(String phoneNumber);
}

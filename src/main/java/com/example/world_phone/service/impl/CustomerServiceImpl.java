package com.example.world_phone.service.impl;

import com.example.world_phone.dto.respone.customer.CustomerRespone;
import com.example.world_phone.dto.respone.order.OrderRespone;
import com.example.world_phone.entity.CustomerEntity;
import com.example.world_phone.repo.CustomerDao;
import com.example.world_phone.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao dao;

    @Override
    public List<CustomerEntity> findByPhoneNumber(String phoneNumber) {
        return dao.findByPhoneNumber(phoneNumber);
    }
}

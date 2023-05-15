package com.example.world_phone.api;

import com.example.world_phone.dto.respone.customer.CustomerRespone;
import com.example.world_phone.dto.respone.order.OrderRespone;
import com.example.world_phone.entity.CustomerEntity;
import com.example.world_phone.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer/search")
@RequiredArgsConstructor
public class CustomerApi {

    @Autowired
    private CustomerService customerService;

    @GetMapping("{phoneNumber}")
    public ResponseEntity<?> getorder(@PathVariable("phoneNumber") String phoneNumber){
        List<CustomerEntity> respone = customerService.findByPhoneNumber(phoneNumber);

        return ResponseEntity.ok().body(respone);
    }
}

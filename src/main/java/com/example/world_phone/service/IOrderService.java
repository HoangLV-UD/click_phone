package com.example.world_phone.service;

import com.example.world_phone.dto.request.order.OrderRequest;
import com.example.world_phone.dto.respone.order.OrderRespone;

import java.text.ParseException;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 22/08/2022
 * Project_name: com.example.world_phone.service
 */
public interface IOrderService {
    List<OrderRespone> findAllOrder();

    OrderRespone findByOrder(String id);

    String updateOrder(OrderRequest request);

    String confirmOrder(OrderRequest request) throws ParseException;

    String deleteOrder(String id);




}

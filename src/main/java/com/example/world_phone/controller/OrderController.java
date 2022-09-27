package com.example.world_phone.controller;

import com.example.world_phone.dto.respone.order.OrderRespone;
import com.example.world_phone.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 21/08/2022
 * Project_name: com.example.world_phone.controller
 */
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    @GetMapping("/orders")
    public String index(Model model){
        List<OrderRespone> respones = orderService.findAllOrder();
        model.addAttribute("list", respones);
        return "views/orders/007_Orders_payment";

    }
}






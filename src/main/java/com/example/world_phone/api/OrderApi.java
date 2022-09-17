package com.example.world_phone.api;

import com.example.world_phone.dto.request.order.OrderRequest;
import com.example.world_phone.dto.respone.order.OrderRespone;
import com.example.world_phone.service.IOrderDetailService;
import com.example.world_phone.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * Description:
 *
 * @author: hieu
 * @since: 24/08/2022
 * Project_name: com.example.world_phone.api
 */
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderApi {
    private final IOrderService orderService;

    private final IOrderDetailService detailService;

    @PostMapping("")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest request){
        orderService.addOrder(request);
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getorder(@PathVariable("id") String id){
        OrderRespone respone = orderService.findByOrder(id);
        return ResponseEntity.ok().body(respone);
    }


    @PostMapping("edit-order-details")
    public ResponseEntity<?> editDetails(@RequestBody OrderRequest request){
        String respone = orderService.updateOrder(request);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @PostMapping("confirm-order-sale-person")
    public ResponseEntity<?> confirmSaleOrders(@RequestBody OrderRequest request) throws ParseException {
        String respone = orderService.confirmOrder(request);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @PostMapping("confirm-export-order")
    public ResponseEntity<?> confirmExportOrders(@RequestBody OrderRequest request){
        String respone = orderService.exportOrder(request);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @PostMapping("shipping")
    public ResponseEntity<?> confirmShipping(@RequestBody OrderRequest request){
        String respone = orderService.shippingOrder(request);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }


    @GetMapping("/{id}/{code}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id, @PathVariable("code") String codeOrder){
        String respone = detailService.deleteDetail(id, codeOrder);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @GetMapping("pay-the-bill/{id}")
    public ResponseEntity<?> doneOrder(@PathVariable("id") String id){
        String respone = orderService.doneOrder(id);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @GetMapping("/cancel-order/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable("id") String id){
        String respone = orderService.deleteOrder(String.valueOf(id));
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }
}
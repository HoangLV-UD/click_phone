package com.example.world_phone.api;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.orderinvoice.OrderInvoiceRequest;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.service.IOrderInvoiceService;
import com.example.world_phone.until.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceApi {
    private final IOrderInvoiceService orderInvoiceService;

    private final ConvertUtil convertUtil;

    @PostMapping("")
    public ResponseEntity<?> createOrderInvoice(@RequestBody OrderInvoiceRequest request){
        String check = orderInvoiceService.createNhap(request);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("Lỗi hệ thống");
    }

    @PutMapping("")
    public ResponseEntity<?> updateOrderInvoice(@RequestBody OrderInvoiceRequest request){
        String check = orderInvoiceService.updateNhap(request);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("Lỗi hệ thống");
    }
}

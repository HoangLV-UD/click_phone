package com.example.world_phone.api;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.orderinvoice.OrderInvoiceRequest;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.service.IOrderInvoiceService;
import com.example.world_phone.until.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/order-invoice")
@RequiredArgsConstructor
@Slf4j
public class OrderInvoiceApi {
    private final IOrderInvoiceService orderInvoiceService;

    private final ConvertUtil convertUtil;


    @PostMapping("")
    public ResponseEntity<?> createOrderInvoice(@RequestBody OrderInvoiceRequest request){
        if (convertUtil.strToDate(request.getReceiveDate(), "dd-MM-yyyy").compareTo(new Date()) < 0) {
            throw new WorldPhoneExp(ConstansErrorCode.VOUCHER_DATE_NOT_PAST);
        }
        if(request.getDetailRequest().size() == 0){
            throw new WorldPhoneExp(ConstansErrorCode.INVOICE_NOT_DETAIL);
        }
        log.info("create order invoice : {}", request);
        String check = orderInvoiceService.createOrderInvoice(request);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("Lỗi hệ thống");
    }

}

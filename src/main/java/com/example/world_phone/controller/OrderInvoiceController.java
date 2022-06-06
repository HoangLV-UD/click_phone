package com.example.world_phone.controller;


import com.example.world_phone.dto.respone.supplier.SupplierResponseDTO;
import com.example.world_phone.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class OrderInvoiceController {

    private final ISupplierService supplierService;


    @GetMapping
    public String homeInvoice(Model model) {
//        List<InvoiceResponse> lstInvoice = goodsreceiptService.findAllInvoice();
//        for (InvoiceResponse x : lstInvoice) {
//            x.setDetails(goodsreceiptService.findAllInvoiceDetailByInvoiceId(x.getInvoiceId()));
//        }
//        List<InvoiceOrderResponse> lstInvoiceOrder = goodsOrderService.getAllInvoiceOrder();
        List<SupplierResponseDTO> lstSupplier = supplierService.findAll();
//        List<ProductResponse> lstProduct = productService.getAllProduct();
//        lstInvoice.sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
//        lstInvoiceOrder.sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
//        lstProduct.sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
//        model.addAttribute("lstInvoice", lstInvoice);
//        model.addAttribute("lstInvoiceOrder", lstInvoiceOrder);
        model.addAttribute("lstSupplier", lstSupplier);
//        model.addAttribute("lstProduct", lstProduct);
        return "views/invoice-order/order_invoice";
    }
}

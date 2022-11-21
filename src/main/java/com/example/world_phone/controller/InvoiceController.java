package com.example.world_phone.controller;

import com.example.world_phone.dto.respone.color.ColorRespone;
import com.example.world_phone.dto.respone.order_invoice.OrderInvoiceRespone;
import com.example.world_phone.dto.respone.product.ProductResponse;
import com.example.world_phone.dto.respone.supplier.SupplierResponseDTO;
import com.example.world_phone.service.IColorService;
import com.example.world_phone.service.IOrderInvoiceService;
import com.example.world_phone.service.IProductService;
import com.example.world_phone.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class    InvoiceController {
    private final ISupplierService supplierService;

    private final IOrderInvoiceService orderInvoiceService;

    private final IProductService productService;

    private final IColorService colorService;

    @GetMapping
    public String homeInvoice(Model model) {
        List<ColorRespone> colorRespones = colorService.findAll();
        List<OrderInvoiceRespone> lstInvoiceOrder = orderInvoiceService.finAll();
        List<SupplierResponseDTO> lstSupplier = supplierService.findAll();
        List<ProductResponse> productResponseList = productService.findAll();

        model.addAttribute("listColor", colorRespones);
        model.addAttribute("lstProdoct", productResponseList);
        model.addAttribute("lstInvoiceOrder", lstInvoiceOrder);
        model.addAttribute("lstSupplier", lstSupplier);
//        model.addAttribute("lstProduct", lstProduct);
        return "views/invoice-order/nhaphang";
    }
}

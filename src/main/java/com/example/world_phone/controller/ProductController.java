package com.example.world_phone.controller;

import com.example.world_phone.dto.respone.category.CategoryDTO;
import com.example.world_phone.dto.respone.category.CategoryResponeDto;
import com.example.world_phone.dto.respone.color.ColorRespone;
import com.example.world_phone.dto.respone.product.ProductResponse;
import com.example.world_phone.dto.respone.supplier.SupplierResponseDTO;
import com.example.world_phone.service.ICategoryService;
import com.example.world_phone.service.IColorService;
import com.example.world_phone.service.IProductService;
import com.example.world_phone.service.ISupplierService;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    private final ICategoryService categoryService;

    private final ISupplierService supplierService;

    private final SessionUtil sessionUtil;

    private final IColorService colorService;

    @GetMapping()
    public String index(Model model){

        List<CategoryResponeDto> categoryDTOList = categoryService.getAllCategory();
        List<SupplierResponseDTO> supplierResponseDTOS = supplierService.findAll();
        List<ProductResponse> productResponseList = productService.findAll();
        List<ColorRespone> colorRespones = colorService.findAll();
        model.addAttribute("listCategory", categoryDTOList);
        model.addAttribute("listSupplier", supplierResponseDTOS);
        model.addAttribute("listProduct", productResponseList);
        model.addAttribute("listColor", colorRespones);
        return "/views/product/product";
    }

}

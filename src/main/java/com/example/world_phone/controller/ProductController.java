package com.example.world_phone.controller;

import com.example.world_phone.dto.respone.attribute.os.OsRespone;
import com.example.world_phone.dto.respone.attribute.pin.PinRespone;
import com.example.world_phone.dto.respone.attribute.ram.RamRespone;
import com.example.world_phone.dto.respone.category.CategoryResponeDto;
import com.example.world_phone.dto.respone.color.ColorRespone;
import com.example.world_phone.dto.respone.product.ProductResponse;
import com.example.world_phone.dto.respone.attribute.screen.ScreenReposne;
import com.example.world_phone.dto.respone.supplier.SupplierResponseDTO;
import com.example.world_phone.service.*;
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

    private final IScreenService screenService;

    private final IOsService osService;

    private final IRamService ramService;

    private final IPinService pinService;

    @GetMapping()
    public String index(Model model){

        List<CategoryResponeDto> categoryDTOList = categoryService.getAllCategory();
        List<SupplierResponseDTO> supplierResponseDTOS = supplierService.findAll();
        List<ProductResponse> productResponseList = productService.findAll();
        List<ColorRespone> colorRespones = colorService.findAll();
        List<ScreenReposne> screenReposnes = screenService.findAllScreen();
        List<OsRespone> osRespones = osService.findAll();
        List<PinRespone> pinRespones = pinService.findAll();
        List<RamRespone> ramRespones = ramService.findAll();
        model.addAttribute("listCategory", categoryDTOList);
        model.addAttribute("listSupplier", supplierResponseDTOS);
        model.addAttribute("listProduct", productResponseList);
        model.addAttribute("listColor", colorRespones);
        model.addAttribute("listScreen", screenReposnes);
        model.addAttribute("listOs", osRespones);
        model.addAttribute("listRam", ramRespones);
        model.addAttribute("listPin", pinRespones);
        return "/views/product/product";
    }

}

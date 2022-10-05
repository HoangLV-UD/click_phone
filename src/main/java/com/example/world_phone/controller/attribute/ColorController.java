package com.example.world_phone.controller.attribute;


import com.example.world_phone.service.IColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("color")
public class ColorController {
    private final IColorService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", service.findAll());
        return "/views/product/attribute/color";

    }
}

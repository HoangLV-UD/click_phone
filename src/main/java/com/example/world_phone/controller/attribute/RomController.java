package com.example.world_phone.controller.attribute;

import com.example.world_phone.service.IChipService;
import com.example.world_phone.service.RomValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 *
 * @author: hieu
 * @since: 07/07/2022
 * Project_name: com.example.world_phone.controller.attribute
 */
@Controller
@RequestMapping("rom")
public class RomController {
    @Autowired
    private RomValueService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());
        return "/views/product/attribute/rom";
    }
}

package com.example.world_phone.controller.attribute;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 *
 * @author: hieu
 * @since: 03/07/2022
 * Project_name: com.example.world_phone.controller.attribute
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("cam")
public class CamController {
    private final ICamService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("list", service.findAll());
        return "/views/product/attribute/cam";
    }
}

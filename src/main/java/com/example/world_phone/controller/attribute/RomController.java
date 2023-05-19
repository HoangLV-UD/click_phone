package com.example.world_phone.controller.attribute;

import com.example.world_phone.service.ILoaiRomService;
import com.example.world_phone.service.IRomService;
import com.example.world_phone.service.IRomValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("rom")
public class RomController {
    @Autowired
    private IRomService service;


    @GetMapping
    public String index(Model model){
//        service.findAll().stream().forEach(System.out::println);
        model.addAttribute("list", service.findAll());



        return "/views/product/attribute/rom";
    }
}

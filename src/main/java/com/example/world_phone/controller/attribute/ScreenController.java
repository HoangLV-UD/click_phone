package com.example.world_phone.controller.attribute;

import com.example.world_phone.service.IScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("screen")
public class ScreenController {

    private final IScreenService screenService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("list", screenService.findAllScreen());
        return "/views/product/attribute/screen";
    }

}
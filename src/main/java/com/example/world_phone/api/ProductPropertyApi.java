package com.example.world_phone.api;


import com.example.world_phone.dto.request.product_property.ProductPropertyRequest;
import com.example.world_phone.dto.respone.order_detail.OrderDetailRespone;
import com.example.world_phone.dto.respone.product.ProductPropertyRespone;
import com.example.world_phone.service.IProductPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-property")
public class ProductPropertyApi {

    private final IProductPropertyService service;


    @GetMapping("{id}")
    public ResponseEntity<?> findByID(@PathVariable("id") Long id){
        OrderDetailRespone respone = service.findById(id);
        return ResponseEntity.ok().body(respone);
    }

    @PostMapping("")
    public ResponseEntity<?> getProductProperty(@RequestBody ProductPropertyRequest request){
        List<ProductPropertyRespone> list = new ArrayList<>();
        if(request.getColorId() == null){
            list = service.findByRom(Long.valueOf(request.getRomId()));
            if(list.size() > 0){
                return ResponseEntity.ok().body(list);
            }
        }else {
            list = service.findByRomAndColor(request.getRomId(), request.getColorId());
            if(list.size() > 0){
                return ResponseEntity.ok().body(list.get(0));
            }
        }

        return ResponseEntity.ok().body("false");
    }

    @PutMapping("")
    public ResponseEntity<?> udpateProductProperty(@RequestBody ProductPropertyRequest request){
        ProductPropertyRespone respone = service.updateProductProperty(request);
        if(respone != null){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body("false");
    }

    @PutMapping("/status")
    public ResponseEntity<?> updateProductPropertyStatus(@RequestBody ProductPropertyRequest request){
        String check = service.udpateStatusProductProperty(request);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }

}

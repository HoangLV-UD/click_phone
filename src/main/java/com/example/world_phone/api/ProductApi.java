package com.example.world_phone.api;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.product.ProductRequestAdd;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApi {
    private final IProductService productService;

    @PostMapping()
    public ResponseEntity<?> index(@RequestBody ProductRequestAdd productRequestAdd){
        String check = productService.createProduct(productRequestAdd);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body(String.valueOf(new WorldPhoneExp(ConstansErrorCode.PRODUCT_ERROR_CREATE)));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id){
        return null;

    }


}

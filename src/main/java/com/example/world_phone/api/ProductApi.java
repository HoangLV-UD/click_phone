package com.example.world_phone.api;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.product.ProductRequestAdd;
import com.example.world_phone.dto.request.product.ProductRequestEdit;
import com.example.world_phone.dto.respone.product.ProductResponse;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApi {
    private final IProductService productService;

    private final HttpServletRequest request;

    @GetMapping() // yêu cầu ass java 5
    public ResponseEntity<?> getByName(){
        String name = request.getParameter("name");
        System.out.println(name);
        ProductResponse response = productService.getName(name);
        if(response == null){
            return ResponseEntity.badRequest().body(String.valueOf(new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST)));
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody ProductRequestAdd productRequestAdd){
        String check = productService.createProduct(productRequestAdd);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body(String.valueOf(new WorldPhoneExp(ConstansErrorCode.PRODUCT_ERROR_CREATE).getErrorMessage().getVn()));
    }

    @PutMapping()
    public ResponseEntity<?> editProduct(@RequestBody ProductRequestEdit productRequestEdit){
        String check = productService.editProduct(productRequestEdit);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body(String.valueOf(new WorldPhoneExp(ConstansErrorCode.PRODUCT_ERROR_CREATE).getErrorMessage().getVn()));
    }

    @PutMapping("/status/{id}/{check}")
    public ResponseEntity<?> editProductStatus(@PathVariable("id") Long id, @PathVariable("check") String valude){
        String check;
        if(valude.equals("OFF")){
            check = productService.editStatusProduct(id, "OFF");
        }else {
            check = productService.editStatusProduct(id, "ON");
        }
        if(check.equals("ok")){
            return ResponseEntity.ok().body(check);
        }
        return ResponseEntity.badRequest().body(check);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        String check = productService.deleteProduct(id);
        if(check.equals("ok")){
            return ResponseEntity.ok().body(check);
        }
            return ResponseEntity.badRequest().body(check);
    }


}

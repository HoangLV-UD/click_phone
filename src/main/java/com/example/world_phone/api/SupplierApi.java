package com.example.world_phone.api;

import com.example.world_phone.dto.request.supplier.EditSupplierDto;
import com.example.world_phone.dto.request.supplier.SupplierRequestDTO;
import com.example.world_phone.dto.respone.supplier.SupplierResponseDTO;
import com.example.world_phone.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/supplier")
public class SupplierApi {
    private final ISupplierService service;


    @GetMapping("/find/email/{email}")
    private Integer checkEmail(@PathVariable("email") String email){
        return service.findByEmail(email);
    }

    @GetMapping("/find/phone/{phone}")
    private Integer checkPhone(@PathVariable("phone") String phone){
        return service.findByPhone(phone);
    }

    @GetMapping("{id}")
    private SupplierResponseDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    private ResponseEntity addSupplier(@RequestBody SupplierRequestDTO requestDTO){
        if(service.addSupplier(requestDTO) != null){
            return ResponseEntity.ok().body(requestDTO);
        }else {
            return ResponseEntity.badRequest().body(requestDTO);
        }
    }

    @PutMapping()
    private ResponseEntity updateSupplier(@RequestBody EditSupplierDto requestDTO){
        if(service.updateSupplier(requestDTO) != null){
            return ResponseEntity.ok().body(requestDTO);
        }else {
            return ResponseEntity.badRequest().body(requestDTO);
        }
    }

    @PutMapping("/delete/{id}")
    private ResponseEntity deleteSupplier(@PathVariable("id") Long id){
        if(service.deleteSupplier(id)){
            return ResponseEntity.ok("ok");
        }else {
            return ResponseEntity.badRequest().body("false");
        }
    }

    @PutMapping("/status/{id}/{status}")
    public ResponseEntity changeStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {
        if (service.changeStatus(id, status)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }
}

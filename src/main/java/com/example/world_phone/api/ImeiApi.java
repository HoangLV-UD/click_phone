package com.example.world_phone.api;

import com.example.world_phone.dto.request.imei.ImeiRequest;
import com.example.world_phone.repo.ImeiRepo;
import com.example.world_phone.service.ImeiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 27/10/2022
 * Project_name: com.example.world_phone.api
 */
@RestController
@RequestMapping("/api/imei")
@RequiredArgsConstructor
public class ImeiApi {
    @Autowired
    private ImeiRepo imeiRepo;

    @Autowired
    private ImeiService service;


    @GetMapping("{id}")
    public ResponseEntity<?> index(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(imeiRepo.findByDeleteFlagIsFalseAndPropertyProductId(id));
    }

    @GetMapping("{id}/{order}")
    public ResponseEntity<?> findImeiDaBan(@PathVariable("id") Long id, @PathVariable("order") Long order){
        return ResponseEntity.ok().body(service.findImeiDaBan(id, order));
    }


    @PostMapping("{id}")
    public ResponseEntity<?> saveImei(@RequestBody List<ImeiRequest> list, @PathVariable("id") Long id){
        if(service.saveImei(list, id)){
            return ResponseEntity.ok().body(new ImeiRequest());
        }else {
            return ResponseEntity.badRequest().body("");
        }

    }
}

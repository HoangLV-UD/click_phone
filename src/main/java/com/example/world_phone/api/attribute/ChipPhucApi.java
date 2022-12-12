package com.example.world_phone.api.attribute;

import com.example.world_phone.dto.request.attribute.chip.ChipRequest;
import com.example.world_phone.dto.respone.attribute.chip.ChipRespone;
import com.example.world_phone.service.IChipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/chip")
public class ChipPhucApi {

    @Autowired
    private IChipService service;

    @GetMapping("/{id}")
    public ChipRespone findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addScreen(@RequestBody ChipRequest request) {
        System.out.println("hihihi");
        String status = service.createChip(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @PutMapping
    public ResponseEntity<?> updateScreen(@RequestBody ChipRequest request) {
        String status = service.updateChip(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") Long id) {
        String status = service.deleteChip(id);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }
}

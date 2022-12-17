package com.example.world_phone.api.attribute;

import com.example.world_phone.dto.request.attribute.cam.CamRequest;
import com.example.world_phone.dto.respone.attribute.cam.CamRespone;
import com.example.world_phone.service.ICamService;
import com.example.world_phone.service.IChipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cam")
public class CamApia {

    @Autowired
    private ICamService service;

    @GetMapping("/{id}")
    public CamRespone findById(@PathVariable("id") String id) {
        return service.findByID(Long.valueOf(id));
    }

    @PostMapping
    public ResponseEntity<?> addScreen(@RequestBody CamRequest request) {
        String status = service.save(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @PutMapping
    public ResponseEntity<?> updateScreen(@RequestBody CamRequest request) {
        String status = service.update(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") Long id) {
        String status = service.delete(id);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }
}

package com.example.world_phone.api.attribute;

import com.example.world_phone.dto.request.attribute.os.OsRequest;
import com.example.world_phone.dto.request.attribute.screen.ScreenRequest;
import com.example.world_phone.dto.respone.attribute.os.OsRespone;
import com.example.world_phone.dto.respone.attribute.screen.ScreenReposne;
import com.example.world_phone.service.IOsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/os")
public class OsApi {

    @Autowired
    private IOsService service;

    @GetMapping("/{id}")
    public OsRespone findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addScreen(@RequestBody OsRequest request) {
        String status = service.save(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @PutMapping
    public ResponseEntity<?> updateScreen(@RequestBody OsRequest request) {
        String status = service.edit(request);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.badRequest().body(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") Long id) {
        System.out.println(id);

        String status = service.delete(id);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }
}

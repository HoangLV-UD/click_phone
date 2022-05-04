package com.example.world_phone.api;

import com.example.world_phone.dto.request.staff.StaffAddRequestDTO;
import com.example.world_phone.dto.request.staff.StaffEditRequestDTO;
import com.example.world_phone.dto.respone.staff.StaffResponeDto;
import com.example.world_phone.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
public class StaffApi {


    @Autowired
    private IStaffService staffService;

    @GetMapping("/{id}")
    public StaffResponeDto findById(@PathVariable("id") String id) {
        return staffService.findById(id);
    }

    @GetMapping("/find/email/{value}")
    public Integer findByEmail(@PathVariable("value") String value) {
        return staffService.findByEmail(value);
    }

    @GetMapping("/find/phone/{value}")
    public Integer findByPhone(@PathVariable("value") String value) {
        return staffService.findByPhone(value);
    }

    @PostMapping
    public ResponseEntity addStaff(@RequestBody StaffAddRequestDTO staff) {
        String status = staffService.addStaff(staff);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(staff);
        }
        return ResponseEntity.badRequest().body(staff);
    }

    @PutMapping
    public ResponseEntity updateStaff(@RequestBody StaffEditRequestDTO staff) {
        staffService.editStaff(staff);
        return ResponseEntity.ok().body(staff);
    }

    @PutMapping("/status/{id}/{status}")
    public ResponseEntity deleteStaff(@PathVariable("id") String id, @PathVariable("status") String status) {
        if (staffService.changeStatusStaff(id, status)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity deleteStaff(@PathVariable("id") String id) {
        if (staffService.deleteStaff(id)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }
}

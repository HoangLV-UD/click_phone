package com.example.world_phone.service;

import com.example.world_phone.dto.request.staff.StaffAddRequestDTO;
import com.example.world_phone.dto.request.staff.StaffEditRequestDTO;
import com.example.world_phone.dto.respone.staff.StaffResponeDto;
import com.example.world_phone.entity.StaffEntity;

import java.util.List;

public interface IStaffService {

    List<StaffResponeDto> findAll();

    // Staff by ID
    StaffResponeDto findById(String id);

    // Staffs deleted
    List<StaffResponeDto> findAllByDeleteFlagIsTrue();

    // Staffs not deleted
    List<StaffResponeDto> findAllByDeleteFlagIsFalse();

    // Staffs active & not delete
    List<StaffResponeDto> findAllByStatusIsTrueAndDeleteFlagIsTrue();

    // Staffs inactive & not delete
    List<StaffResponeDto> findAllByStatusIsFalseAndDeleteFlagIsTrue();

    // Add staff
    String addStaff(StaffAddRequestDTO staff);

    // Edit staff
    String editStaff(StaffEditRequestDTO staff);


    // Delete staff
    boolean deleteStaff(String id);

    // Block/Unblock staff by status
    boolean changeStatusStaff(String id, String status);

    // Find by email
    Integer findByEmail(String email);

    // Find by phone number
    Integer findByPhone(String phoneNumber);

    // Get staff by email
    StaffEntity getByEmail(String email);

    // save staff
    StaffEntity saveStaff(StaffEntity staffEntity);
}

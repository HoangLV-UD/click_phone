package com.example.world_phone.repo;

import com.example.world_phone.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<StaffEntity, Long> {

    StaffEntity findByIdAndDeleteFlagIsFalse(Long id);

    List<StaffEntity> findAllByDeleteFlagIsTrue();

    // Staffs not deleted
    List<StaffEntity> findAllByDeleteFlagIsFalse();

    // Staffs active & not delete
    List<StaffEntity> findAllByStatusIsTrueAndDeleteFlagIsTrue();

    // Staffs inactive & not delete
    List<StaffEntity> findAllByStatusIsFalseAndDeleteFlagIsTrue();


    // Find staff by email
    List<StaffEntity> findByEmailAndDeleteFlagIsFalse(String email);

    // Find staff by phoneNumber
    List<StaffEntity> findByPhoneNumber(String phoneNumber);


}

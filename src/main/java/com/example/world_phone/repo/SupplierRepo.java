package com.example.world_phone.repo;

import com.example.world_phone.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<SupplierEntity, Long> {

    List<SupplierEntity> findByDeleteFlagIsFalse();


    Optional<SupplierEntity> findByName(String name);

    Optional<SupplierEntity> findByEmail(String email);

    Optional<SupplierEntity> findByPhoneNumber(String phoneNumber);

    Optional<SupplierEntity> findByIdAndDeleteFlagIsFalse(Long id);
}

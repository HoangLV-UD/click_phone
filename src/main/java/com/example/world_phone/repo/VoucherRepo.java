package com.example.world_phone.repo;

import com.example.world_phone.entity.VoucherEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface VoucherRepo extends JpaRepository<VoucherEntity, String> {

    List<VoucherEntity> findAllByCodeAndDeleteFlagIsFalse(String voucherCode);

    List<VoucherEntity> findAllByDeleteFlagIsFalse();

    VoucherEntity getByIdAndDeleteFlagIsFalse(String id);
}

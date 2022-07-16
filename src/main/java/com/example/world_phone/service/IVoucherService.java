package com.example.world_phone.service;

import com.example.world_phone.dto.request.voucher.VoucherRequest;
import com.example.world_phone.dto.respone.voucher.VoucherResponse;


import java.util.List;


public interface IVoucherService {

    String createVoucher(VoucherRequest voucherRequest);

    String updateVoucher(VoucherRequest voucherRequest);

    String deleteVoucher(String id);

    List<VoucherResponse> getAllVoucher();

    String activeVoucher(String id);

    List<Long> findAllIdCustomerByVoucherId(String voucherId);

    VoucherResponse getById(String voucherId);

    List<VoucherResponse> findAllByCode(String code);

    VoucherResponse findFistByCode(String code);

}

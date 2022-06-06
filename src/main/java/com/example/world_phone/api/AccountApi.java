package com.example.world_phone.api;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.entity.StaffEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.service.impl.StaffServiceImpl;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AccountApi {



    private final StaffServiceImpl staffService;

    private String message;

    @GetMapping("/count_login_false")
    public ResponseEntity<String> getCountLoginFalse(@RequestParam("email") String email) {
        StaffEntity staff = staffService.getByEmail(email);
        if (staff != null) {

            // Check block account
            if (staff.getStatus().equals("0")) {
                message = String.valueOf(new WorldPhoneExp(ConstansErrorCode.LOGIN_ACCOUNT_BLOCKED).getErrorMessage().getVn());
                log.info(message);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
            }
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        message = String.valueOf(new WorldPhoneExp(ConstansErrorCode.LOGIN_EMAIL_NOT_EXITS).getErrorMessage().getVn());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}

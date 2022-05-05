package com.example.world_phone.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private String vn;
    private String en;
    private String code;
}

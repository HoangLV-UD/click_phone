package com.example.world_phone.common;

/**
 * Description:
 *
 * @author: hieu
 * @since: 22/10/2022
 * Project_name: com.example.world_phone.common
 */
public enum StatusImei {
    DA_BAN("1"),
    CHUA_BAN("0"),
    DA_CO_DON("2");

    private final String value;

    StatusImei(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

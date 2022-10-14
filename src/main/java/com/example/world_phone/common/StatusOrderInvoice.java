package com.example.world_phone.common;

/**
 * Description:
 *
 * @author: hieu
 * @since: 11/10/2022
 * Project_name: com.example.world_phone.common
 */
public enum StatusOrderInvoice {
    HUY(0),
    DOI_DUYET(-1),
    DA_DAT(1),
    DA_NHAN(2);


    private final int index;

    StatusOrderInvoice(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

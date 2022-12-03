package com.example.world_phone.dto.respone.imei;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: hieu
 * @since: 24/10/2022
 * Project_name: com.example.world_phone.dto.respone.imei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImeiResponse {
    private String id;
    private String value;
}
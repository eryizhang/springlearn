package com.alisound.thymeleaf.model.alidevice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/28 17:10
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliDevice {
    private AliDeviceHeader header = new AliDeviceHeader();
    private AliDevicePayload payload = new AliDevicePayload();

}

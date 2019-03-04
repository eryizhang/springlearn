package com.alisound.thymeleaf.model.alidevice;


import com.alisound.thymeleaf.model.alidevice.alideviceresp.PayloadDevice;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.util.List;


/**
 * @Auther: zhangyahui
 * @Date: 2019/2/28 17:14
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliDevicePayload {
    private String accessToken;
    private String deviceId;
    private String deviceType;
    private String attribute;
    private String value;
    private List<PayloadDevice> devices;
    private PayloadExtension extensions;

    private String errorCode;
    private String message;

    public AliDevicePayload() {
    }

    public AliDevicePayload(String deviceId) {
        this.deviceId = deviceId;
    }
}

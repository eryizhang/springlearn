package com.alisound.thymeleaf.model.alidevice;

import lombok.Data;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/28 17:13
 * @Description:
 */
@Data
public class AliDeviceHeader {
    private String namespace;
    private String name;
    private String messageId;
    private Integer payLoadVersion;
}

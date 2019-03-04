package com.alisound.thymeleaf.constant;

import lombok.Data;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/28 19:20
 * @Description:
 */
@Data
public class ErrorInfo {
    private String errorCode;

    private String message;

    private String chineseMsg;

    public ErrorInfo() {
    }

    public ErrorInfo(String errorCode, String message, String chineseMsg) {
        this.errorCode = errorCode;
        this.message = message;
        this.chineseMsg = chineseMsg;
    }
}

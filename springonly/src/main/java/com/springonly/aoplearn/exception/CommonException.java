package com.springonly.aoplearn.exception;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/18 15:41
 * @Description:
 */
public class CommonException extends RuntimeException {
    private String code;

    private String message;



    public CommonException() {

    }

    public CommonException(String message,Throwable cause) {
        super(cause);
        this.message = message;
    }

    public CommonException(String message, String code,Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public CommonException(String message) {
        this.message = message;
    }

    public CommonException(String message, String code) {
        this.code = code;
        this.message = message;
    }
}

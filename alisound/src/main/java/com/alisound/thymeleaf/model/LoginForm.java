package com.alisound.thymeleaf.model;

import lombok.Data;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/27 14:25
 * @Description:
 */
@Data
public class LoginForm {
    private String houseId;

    private String unicode;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }
}

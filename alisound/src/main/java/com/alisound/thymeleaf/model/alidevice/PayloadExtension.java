package com.alisound.thymeleaf.model.alidevice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/28 17:17
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayloadExtension {
    private String extension1;
    private String extension2;
    private String parentId;
}

package com.alisound.thymeleaf.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/1 17:22
 * @Description:
 */
public interface CommandSwitchMap {

    /*
     * 将阿里的命令转成处理平台的命令
     * key：阿里请求中的name+attribute+value+处理平台的设备metedataID
     * 大多数阿里的命令都是对应处理平台的一个命令，在iot_device_metadata_command中配置
     * 如果是对应平台的多个命令，在此处配置。
     */
    Map<String, CommandParam[]> params = new HashMap<String, CommandParam[]>() {
        private static final long serialVersionUID = 8117451387362560778L;

        {

        }
    };
}

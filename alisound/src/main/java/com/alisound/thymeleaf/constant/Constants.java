package com.alisound.thymeleaf.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/28 17:35
 * @Description:
 */
public interface Constants {
    Map<String, ErrorInfo> ERROR_MESSAGE = new HashMap<String, ErrorInfo>() {
        private static final long serialVersionUID = 1040504082507746039L;

        {
            put(Error.INVALIDATE_CONTROL_ORDER, new ErrorInfo(Error.INVALIDATE_CONTROL_ORDER, "invalidate control order", "控制指令不正确"));

            put(Error.SERVICE_ERROR, new ErrorInfo(Error.SERVICE_ERROR, "服务错误原因（方便观察原因）", "服务异常"));

            put(Error.DEVICE_NOT_SUPPORT_FUNCTION, new ErrorInfo(Error.DEVICE_NOT_SUPPORT_FUNCTION, "device not support", "设备不支持该操作"));

            put(Error.INVALIDATE_PARAMS, new ErrorInfo(Error.INVALIDATE_PARAMS, "invalidate params", "请求参数有误"));

            put(Error.DEVICE_IS_NOT_EXIST, new ErrorInfo(Error.DEVICE_IS_NOT_EXIST, "device is not exist", "设备未找到"));

            put(Error.IOT_DEVICE_OFFLINE, new ErrorInfo(Error.IOT_DEVICE_OFFLINE, "device is offline", "设备离线状态"));

            put(Error.ACCESS_TOKEN_INVALIDATE, new ErrorInfo(Error.ACCESS_TOKEN_INVALIDATE, "access_token is invalidate", "access_token 无效（包括失效）"));

        }
    };

    /*
     * 命令映射，阿里的不同命令在处理平台可能是同一个命令，例如开，关两个的command是相同的
     */
    Map<String, String> commandMap = new HashMap<String, String>() {
        private static final long serialVersionUID = 316956480961096135L;

        {
            put("TurnOn", "TurnSwitch");
            put("TurnOff", "TurnSwitch");
        }
    };

    interface Error {
        String INVALIDATE_CONTROL_ORDER = "INVALIDATE_CONTROL_ORDER";

        String SERVICE_ERROR = "SERVICE_ERROR";

        String DEVICE_NOT_SUPPORT_FUNCTION = "DEVICE_NOT_SUPPORT_FUNCTION";

        String INVALIDATE_PARAMS = "INVALIDATE_PARAMS";

        String DEVICE_IS_NOT_EXIST = "DEVICE_IS_NOT_EXIST";

        String IOT_DEVICE_OFFLINE = "IOT_DEVICE_OFFLINE";

        String ACCESS_TOKEN_INVALIDATE = "ACCESS_TOKEN_INVALIDATE";
    }

    interface Namespace {
        String deviceDiscovery = "AliGenie.Iot.Device.Discovery";

        String deviceControl = "AliGenie.Iot.Device.Control";

        String deviceQuery = "AliGenie.Iot.Device.Query";
    }

    interface Name {
        String deviceDiscoveryReq = "DiscoveryDevices";
        String deviceDiscoveryResp = "DiscoveryDevicesResponse";
        String controlError = "ErrorResponse";
        String controlSuccessSufix = "Response";
    }


}

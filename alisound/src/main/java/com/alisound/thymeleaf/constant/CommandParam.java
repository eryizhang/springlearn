package com.alisound.thymeleaf.constant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/1 17:30
 * @Description:
 */
@Data
public class CommandParam {
    private String[] names;
    private String[] values;
    private String command;

    public CommandParam() {
    }

    public CommandParam(String[] names, String[] values, String command) {
        this.names = names;
        this.values = values;
        this.command = command;
    }

    /*public CommandParam(IotDeviceMetadataCommand iotDeviceMetadataCommand, AliDevice req) {
        this.command = iotDeviceMetadataCommand.getCommand();
        JSONObject json = JSON.parseObject(iotDeviceMetadataCommand.getAliCommandParam());
        JSONObject nameJson = json.getJSONObject("name");
        String nameString = nameJson.getString(req.getPayload().getAttribute());
        ErrorInfo errorInfo = Constants.ERROR_MESSAGE.get(Constants.Error.INVALIDATE_PARAMS);
        if (nameString == null) {
            throw new CommonException(errorInfo.getErrorCode(), errorInfo.getMessage());
        }
        this.names = nameString.split(",");

        JSONObject valueJson = json.getJSONObject("value");
        String valueString = valueJson.getString(req.getPayload().getValue());
        if (valueString == null) {
            throw new CommonException(errorInfo.getMessage(), errorInfo.getErrorCode());
        }
        this.values = valueString.split(",");
    }*/
}

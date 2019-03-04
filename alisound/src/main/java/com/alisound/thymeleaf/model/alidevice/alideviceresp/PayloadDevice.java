package com.alisound.thymeleaf.model.alidevice.alideviceresp;

import com.alibaba.fastjson.JSON;
import com.alisound.thymeleaf.model.alidevice.PayloadExtension;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/28 17:20
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayloadDevice {
    private String deviceId;
    private String deviceName;
    private String deviceType;
    private String zone;
    private String brand;
    private String model;
    private String icon;
    private List<DeviceProperty> properties;
    private String[] actions = {"TurnOn", "TurnOff"};
    private PayloadExtension extensions;

    static Map<String, String> statusMap = new HashMap<String, String>() {
        private static final long serialVersionUID = -4800088921630851109L;

        {
            put("00", "off");
            put("01", "on");
            put("02", "on");
        }
    };

    public PayloadDevice() {
    }

/*    public PayloadDevice(AliDeviceModel iotDeviceInfo) {
        this.deviceId = iotDeviceInfo.getIdeviceInfoId();
        this.deviceName = iotDeviceInfo.getDeviceInfoName();
        this.deviceType = iotDeviceInfo.getAliMetedataName();
        this.zone = "大厅";
        this.brand = iotDeviceInfo.getProducerName();
        this.model = "model";
        this.icon = "http://58.213.162.164:10000/files/IotDeviceType/20181110/ad07285b-b873-4042-8a26-00dd18b27ee4.png";
        this.properties = (deviceProperty(iotDeviceInfo.getDeviceInfoNow()));
    }*/


    List<DeviceProperty> deviceProperty(String param) {

/*        List<DeviceProperty> deviceProperties = new ArrayList<>();
        DeviceProperty deviceProperty = new DeviceProperty();
        deviceProperty.setName("powerstate");
        String value = null;
        if (!StringUtil.isEmpty(param)) {
            CommandDetails commandDetails = JSON.parseObject(param, CommandDetails.class);
            String status = commandDetails.getSwitchStatus();
            if (status != null) {

                value = statusMap.get(status);
            } else {
                value = "off";
            }
        } else {
            value = "off";
        }
        deviceProperty.setValue(value);
        deviceProperties.add(deviceProperty);
        return deviceProperties;*/

return null;
    }
}

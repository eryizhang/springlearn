package com.alisound.thymeleaf.controller;

import com.alibaba.fastjson.JSON;
import com.alisound.thymeleaf.constant.Constants;
import com.alisound.thymeleaf.model.alidevice.AliDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/28 17:11
 * @Description:
 */
@RestController
@RequestMapping("/aliboxDevice")
public class AliboxDeviceController {



    private static final Logger LOG = LoggerFactory.getLogger(AliboxDeviceController.class);

    @RequestMapping(value = "/deviceHandle", method = RequestMethod.POST)
    public AliDevice deviceHandle(@RequestBody AliDevice aliDeviceReq) {
        LOG.info("请求数据：" + JSON.toJSONString(aliDeviceReq));

        String action = aliDeviceReq.getHeader().getNamespace();
        AliDevice resp = new AliDevice();
        resp.setHeader(aliDeviceReq.getHeader());
        switch (action) {
            case Constants.Namespace.deviceDiscovery: {
                //aliboxDeviceService.deviceDiscovery(aliDeviceReq.getPayload().getAccessToken(), resp);
                resp.getHeader().setName(Constants.Name.deviceDiscoveryResp);
                break;
            }
            case Constants.Namespace.deviceControl: {

                //aliboxDeviceService.deviceControl(aliDeviceReq, resp);
                break;
            }
            case Constants.Namespace.deviceQuery: {
                break;
            }
            default: {

            }
        }

        LOG.info("返回数据：" + JSON.toJSONString(resp));
        return resp;
    }


}

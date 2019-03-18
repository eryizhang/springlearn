package com.springonly.aoplearn.controller;

import com.springonly.SpringonlyApplication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhangyahui
 * @Date: 2019/3/18 15:02
 * @Description:
 */

@RestController
@Api(value = "AOPController", description = "AOP测试")
@RequestMapping(value = "/zyh/aoptest")
public class AOPController {
    private static Log log= LogFactory.getLog(AOPController.class);


    @ApiOperation(value = "测试swagger", notes = "测试swagger", httpMethod = "POST")
    @RequestMapping(value = "/testSwagger", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "message", dataType = "String", required = true, paramType = "form") })
    public String testSwagger(HttpServletRequest request, @RequestParam("message") String message) {
        String name = request.getHeader("userName");
        return message + name;
    }

    @ApiOperation(value = "helloWord", notes = "helloWord", httpMethod = "GET")
    @RequestMapping(value = "/helloWord", method = RequestMethod.GET)
    public String helloWord() {
        log.info("*********"+"hello world"+"*********");
        return "hello world";
    }
}

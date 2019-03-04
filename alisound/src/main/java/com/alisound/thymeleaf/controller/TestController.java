package com.alisound.thymeleaf.controller;

/**
 * @Auther: zhangyahui
 * @Date: 2019/2/25 13:41
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alisound.thymeleaf.model.LoginForm;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Controller
@RequestMapping("/test")
public class TestController {


    AtomicInteger visitTime=new AtomicInteger(0);

    String grant_type = "authorization";
    String clientId = "clientId";
    String clientSecret = "clientSecret";
    String accessTokenUrl = "http://58.213.162.164:10033/test/responseAccessToken";
    String userInfoUrl = null;
    String redirectUrl = "https://open.bot.tmall.com/oauth/callback";
    String response_type = "code";
    String code = null;
    String cutURL = "http://58.213.162.164:10033/test/responseCode";
    String OAuthURL = "http://58.213.162.164:10033/test/responseCode?";
    int cutlength = cutURL.length();

    String aliuri = "https://open.bot.tmall.com/oauth/callback?skillId=30766&token=MjU5OTQ4NDQ3NUFGRUhJTkZEVlE=";
    Map<String, String> map = new ConcurrentHashMap<>();
    String houseId = "1234556789012345678";

    @RequestMapping("/responseCode")
    public Object toShowUser(HttpServletRequest request) throws IOException, InterruptedException {
        System.out.println("----------服务端/responseCode--------------------------------------------------------------");
        try {
            //构建OAuth 授权请求
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            String token = oauthRequest.getParam("token");

            System.out.println(oauthRequest.getClientId());
            System.out.println(oauthRequest.getResponseType());
            System.out.println(oauthRequest.getRedirectURI());
            System.out.println(oauthRequest.getState());
            System.out.println(oauthRequest.getClientSecret());
            System.out.println(oauthRequest.getParam("token"));


            if (oauthRequest.getClientId() != null && oauthRequest.getClientId() != "") {
                //设置授权码
                String authorizationCode = UUID.randomUUID().toString().replace("-", "").substring(0, 18);
                System.out.println(authorizationCode);
                //利用oauth授权请求设置responseType，目前仅支持CODE，另外还有TOKEN
                String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
                //进行OAuth响应构建
                OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                        OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
                //设置授权码
                builder.setParam("state", oauthRequest.getState());
                builder.setParam("code", authorizationCode);
                //得到到客户端重定向地址
                String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
                //构建响应
                final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
                System.out.println("服务端/responseCode内，返回的回调路径：" + response.getLocationUri());
                System.out.println("----------服务端/responseCode--------------------------------------------------------------");
                String responceUri = response.getLocationUri();
                System.out.println(responceUri);
                //根据OAuthResponse返回ResponseEntity响应
                HttpHeaders headers = new HttpHeaders();

                try {
                    headers.setLocation(new URI(response.getLocationUri()));

                } catch (URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                return "redirect:" + responceUri;
                // https://open.bot.tmall.com/oauth/callback?skillId=18105&code=0b58444322e04d9c8e&state=11&token=MjM0MDgzODYwMEFGRUhJTkZEVlE%3D
            }

        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }
        System.out.println("----------服务端/responseCode--------------------------------------------------------------");
        return null;
    }

    @RequestMapping(value = "/responseAccessToken", method = RequestMethod.POST)
    public HttpEntity token(HttpServletRequest request) throws OAuthSystemException {
        JSONObject jsonObject = new JSONObject();
        System.out.println("--------服务端/responseAccessToken-----------------------------------------------------------");
        OAuthIssuer oauthIssuerImpl = null;
        OAuthResponse response = null;
        //构建OAuth请求
        try {
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
            String authCode = oauthRequest.getParam(OAuth.OAUTH_CODE);
            System.out.println("*************code*********************" + code);
            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
            System.out.println("*************redirectURI*********************" + redirectURI);
            System.out.println("*************type*********************" + oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE));
            System.out.println("*************CLIENTID*********************" + oauthRequest.getParam(OAuth.OAUTH_CLIENT_ID));
            System.out.println("*************OAUTH_STATE*********************" + oauthRequest.getParam(OAuth.OAUTH_STATE));
            System.out.println("*************code*********************" + request.getParameter("code"));
            String clientSecret = oauthRequest.getClientSecret();
            if (clientSecret != null || clientSecret != "") {
                //生成Access Token
                oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                final String accessToken = oauthIssuerImpl.accessToken();
                final String refreshToken = oauthIssuerImpl.refreshToken();

                jsonObject.put("access_token", request.getParameter("code"));
                jsonObject.put("refresh_token", request.getParameter("code"));
                jsonObject.put("expires_in", 1760000);
                System.out.println(jsonObject.toString());

                System.out.println("--oooo---");
                //生成OAuth响应
                response = OAuthASResponse
                        .tokenResponse(HttpServletResponse.SC_OK)
                        .setAccessToken("123")
                        .setRefreshToken("123")
                        .setParam("expires_in", "17600000")
                        .buildJSONMessage();
                System.out.println(response.getBody());

            }


            System.out.println("--------服务端/responseAccessToken-----------------------------------------------------------");

            //根据OAuthResponse生成ResponseEntity
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));

        } catch (OAuthSystemException e) {
            response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setParam("error", "101")
                    .setParam("error_description", "内部错误")
                    .buildJSONMessage();

            // TODO Auto-generated catch block
            jsonObject.put("error", 101);
            jsonObject.put("error_dercription", "内部错误");
            System.out.println(jsonObject.toString());
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthProblemException e) {
            // TODO Auto-generated catch block
            response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setParam("error", "102")
                    .setParam("error_description", "参数错误")
                    .buildJSONMessage();
            jsonObject.put("error", 102);
            jsonObject.put("error_dercription", "参数错误");
            System.out.println(jsonObject.toString());
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
        }
        // System.out.println("--------服务端/responseAccessToken-----------------------------------------------------------");

        // return null;

    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getMessage(HttpServletRequest request, HttpServletResponse response, BufferedReader br) {

        //System.out.println("hi");
        //Header部分
        JSONObject MerchineList = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject header = new JSONObject();
        JSONObject payload = new JSONObject();
        List<JSONObject> devices = new ArrayList();
        List<JSON> properties = new ArrayList();
        List actions = new ArrayList();
        JSONObject extentions = new JSONObject();
        System.out.print(request.getHeaderNames());
        Enumeration<?> enum1 = request.getHeaderNames();
        while (enum1.hasMoreElements()) {
            String key = (String) enum1.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + "\t" + value);
        }
        //body部分
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        System.out.println("str:" + str);
        JSONObject recieveHeader = new JSONObject();
        recieveHeader = JSON.parseObject(str);
        String str1 = recieveHeader.getString("header");
        System.out.println("header:" + recieveHeader.getString("header"));
        JSONObject recieveMessageId = new JSONObject();
        recieveMessageId = JSON.parseObject(str1);
        System.out.println("messageId:" + recieveMessageId.getString("messageId"));

        header.put("namespace", "AliGenie.Iot.Device.Discovery");
        header.put("name", "DiscoveryDevicesResponse");
        header.put("messageId", recieveMessageId.getString("messageId"));
        header.put("payLoadVersion", "1");


        JSONObject device = new JSONObject();
        JSONObject propertie = new JSONObject();


        device.put("deviceId", "34ea34cf2e63");
        device.put("deviceName", "单孔插座11111");
        device.put("deviceType", "outlet");
        device.put("zone", "zone");
        device.put("brand", "brand");
        device.put("model", "model");
        device.put("icon", "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1531878000&di=c989660f4b827a0049c3b7aec4fe38e1&src=http://img.czvv.com/sell/599adfe4d2f0b1b2f118606f/20170905113247194.jpg");


        propertie.put("name", "powerstate");
        propertie.put("value", "off");
        properties.add(propertie);
        device.put("properties", properties);

        actions.add("TurnOn");
        actions.add("TurnOff");
        device.put("actions", actions);


        extentions.put("extension1", "tset");
        extentions.put("extension2", "test");
        device.put("extentions", extentions);

        devices.add(device);
        payload.put("devices", devices);

        MerchineList.put("header", header);
        MerchineList.put("payload", payload);
        System.out.println(MerchineList.toString());
        return MerchineList;

    }

    @RequestMapping(value = "/login")
    public String login(@ModelAttribute LoginForm loginForm, HttpServletRequest request) {
        System.out.println("***********login************");
        String unicede =  visitTime.get()+ "";
        loginForm.setUnicode(unicede);


        System.out.println("----------loginCode/responseCode--------------------------------------------------------------");
        try {
            //构建OAuth 授权请求
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            String token = oauthRequest.getParam("token");

            System.out.println(oauthRequest.getClientId());
            System.out.println(oauthRequest.getResponseType());
            System.out.println(oauthRequest.getRedirectURI());
            System.out.println(oauthRequest.getState());
            System.out.println(oauthRequest.getClientSecret());
            System.out.println(oauthRequest.getParam("token"));


            if (oauthRequest.getClientId() != null && oauthRequest.getClientId() != "") {
                //设置授权码
                String authorizationCode = UUID.randomUUID().toString().replace("-", "").substring(0, 18);
                System.out.println(authorizationCode);
                //利用oauth授权请求设置responseType，目前仅支持CODE，另外还有TOKEN
                String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
                //进行OAuth响应构建
                OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                        OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
                //设置授权码
                builder.setParam("state", oauthRequest.getState());
                //builder.setParam("code", loginForm.getHouseId());
                //得到到客户端重定向地址
                String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
                //构建响应
                final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
                System.out.println("服务端/add，返回的回调路径：" + response.getLocationUri());
                System.out.println("----------服务端/add--------------------------------------------------------------");
                String responceUri = response.getLocationUri();
                System.out.println(responceUri);



                //return "redirect:" + responceUri;
                map.put(unicede, responceUri);
                // https://open.bot.tmall.com/oauth/callback?skillId=18105&code=0b58444322e04d9c8e&state=11&token=MjM0MDgzODYwMEFGRUhJTkZEVlE%3D
            }

        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }


        return "login";

    }

    @RequestMapping(value = "/loginCode")
    public String add(@ModelAttribute LoginForm loginForm) throws OAuthProblemException, OAuthSystemException {
        System.out.println("***********add************");
        houseId = loginForm.getHouseId();
        System.out.println(loginForm.getHouseId() + "        " + loginForm.getUnicode());
        String redirectURL = map.get(loginForm.getUnicode());
        redirectURL=redirectURL+"&code="+loginForm.getHouseId();

        System.out.println("----------服务端/responseCode--------------------------------------------------------------");
        return "redirect:" + redirectURL;
    }
}






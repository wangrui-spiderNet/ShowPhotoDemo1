package com.example.showphotodemo.net.api;

import com.example.showphotodemo.Constants;
import com.example.showphotodemo.entity.UserInfo;
import com.example.showphotodemo.entity.WechatMoment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jn-wr on 2015/10/10.
 */
public class JNApiImpl implements JNApi {

    private final static String APP_KEY = "ANDROID_KCOUPON";
    private final static String TIME_OUT_EVENT = "CONNECT_TIME_OUT";
    private final static String TIME_OUT_EVENT_MSG = "连接服务器失败";

    private HttpEngine httpEngine;

    public JNApiImpl() {
        httpEngine = HttpEngine.getInstance();
    }

    @Override
    public String getUser() {
        try {
            Map<String, String> values = new HashMap<String, String>();
            return httpEngine.postHandle(values,Constants.SERVER_HOST_ADDRESS+ Constants.API_USER_INFO);
        } catch (Exception e) {
            return  e.getMessage();
        }
    }


    @Override
    public String getWeChatMomentList() {
        try {
            Map<String, String> values = new HashMap<String, String>();
            return httpEngine.postHandle(values,Constants.SERVER_HOST_ADDRESS+ Constants.API_USER_TWEETS);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

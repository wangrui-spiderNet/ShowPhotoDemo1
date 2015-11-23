package com.example.showphotodemo.net.api;

import android.util.Log;

import com.example.showphotodemo.Constants;
import com.example.showphotodemo.util.StringUtil;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http引擎处理类
 *
 * @author wangrui
 * @date 15/11/22
 * @version 1.0
 */
public class HttpEngine {
    private final static String TAG = "HttpEngine";
    private final static String REQUEST_MOTHOD = "POST";
    private final static String ENCODE_TYPE = "UTF-8";
    private final static int TIME_OUT = 20000;
    private int  errorCode;

    private static HttpEngine instance = null;

    private HttpEngine() {

    }

    public static HttpEngine getInstance() {
        if (instance == null) {
            instance = new HttpEngine();
        }
        return instance;
    }

    public String postHandle(Map<String, String> mValues,String apiUrl){

        Log.e(Constants.APP_TAG,apiUrl+"接口参数："+mValues.toString());

        HttpGet mHttpGet = new HttpGet(apiUrl);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        HttpResponse httpResponse = null;

        for (Map.Entry<String, String> entry : mValues.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
//            mHttpGet.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            params.clear();
            httpResponse = new DefaultHttpClient().execute(mHttpGet);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(httpResponse.getEntity());
            } else {
                errorCode = Constants.ErrorCode.ERROR_LOCAL_CONNECT_SERVICE_ERROR;
            }

        } catch (UnsupportedEncodingException e) {
            errorCode = Constants.ErrorCode.ERROR_LOCAL_UNSUPPORTEDENCODINGEXCEPTION;
        } catch (ParseException e) {
            errorCode = Constants.ErrorCode.ERROR_LOCAL_PARSEEXCEPTION;
        } catch (IOException e) {
            errorCode = Constants.ErrorCode.ERROR_LOCAL_IOEXCEPTION;
        }

        return null;
    }


    // 拼接参数列表
    private String joinParams(Map<String, String> paramsMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : paramsMap.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append("=");
            try {
                stringBuilder.append(URLEncoder.encode(paramsMap.get(key), ENCODE_TYPE));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            stringBuilder.append("&");
        }
        if(!StringUtil.isEmpty(stringBuilder.toString())){
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }

        return "";
    }
}

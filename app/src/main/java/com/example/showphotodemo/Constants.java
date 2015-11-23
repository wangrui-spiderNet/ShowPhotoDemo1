package com.example.showphotodemo;

/**
 * Created by 94540 on 2015/11/22.
 */
public class Constants {
    public static final String APP_TAG="weichat_moment";
    public static final int DATABASE_VERSION=1;

    public static final String firstfolder="demo";
    public static final String secondfolder="cache";

    public static class ErrorCode {
        public static final int ERROR_API_OK = 1;
        public static final int ERROR_DEVICE_REGISTER_SERVICE_ERROR = 0;
        public static final int ERROR_DEVICE_REGISTER_VALUE_ERROR = -1;
        public static final int ERROR_DEVICE_REGISTER_TOKEN_REPEATE = -2;
        public static final int ERROR_API_ACCESS_TOKEN_OR_PUSH_TOKEN_IS_NULL = 0;
        public static final int ERROR_API_NOT_FIND_DEVICE_INFO = -1;
        public static final int ERROR_API_NO_HAVE_USER_INFO = -2;
        public static final int ERROR_API_ID_OR_PASSWORD_IS_NULL = -3;
        public static final int ERROR_API_ID_OR_PASSWORD_IS_ERROR = -4;
        public static final int ERROR_API_USER_STOP_USE = -5;
        public static final int ERROR_API_REGIST_FORM_ERROR = -6;
        public static final int ERROR_API_REGIST_SERVICE_ERROR = -7;
        public static final int ERROR_API_GET_VERCODE_FORM_ERROR = -8;
        public static final int ERROR_API_SEND_MESSEGE_ERROR = -9;
        public static final int ERROR_API_NOT_FIND_QUESTION_INFO = -10;
        public static final int ERROR_API_UPDATE_PUSH_TOKEN_FORM_ERROR = -11;
        public static final int ERROR_API_PUSH_TOKEN_REPETE_OR_UPDATE_ERROR = -12;

        public static final int ERROR_LOCAL_NETWORK_ERROR = -500;
        public static final int ERROR_LOCAL_JSON_CHACK_ERROR = -501;
        public static final int ERROR_LOCAL_CONNECT_SERVICE_ERROR = -502;
        public static final int ERROR_LOCAL_UNSUPPORTEDENCODINGEXCEPTION = -503;
        public static final int ERROR_LOCAL_PARSEEXCEPTION = -504;
        public static final int ERROR_LOCAL_IOEXCEPTION = -505;

    }

    public interface BROADCASTKEY {
          String EXIT_USER="com.example.showphotodemo.exitout";
    }

    /**
     * 接口方法
     */
    public static String SERVER_HOST_ADDRESS = "";

    public static final String API_USER_INFO="/user/jsmith";
    public static final String API_USER_TWEETS="/user/jsmith/tweets";
}

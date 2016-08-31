package com.example.zhangping.utils;

/**
 * Created by zhangping on 15/12/10.
 */
public class MacroClass {

    public static final String SHAREDPREFERENCES = "setting";
    public  static final String URL_PREXI = "http://115.29.147.39:8080/facelove/";
    public  static final String HEAD_URL ="headurl";
    public static final String NICK_NAME ="nickname";
    public static final String SCHOOL = "school";
    public static final String SEX = "sex";
    public static final String SGIN = "sgin";

    public static final String USER_ID = "userid";
    public static final String FLOW = "registerflow";

    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";

    public static String headUrl(String url)
    {
        return URL_PREXI + "head/"+url;
    }
    public static String figureUrl(String url)
    {
        return URL_PREXI + "diy/" + url;
    }

    public static final String APPKEY = "mgb7ka1nbs2hg";
    public static final String Secret = "HwH4fdy0pa0";

    public static final String SUCCESS ="success";
}

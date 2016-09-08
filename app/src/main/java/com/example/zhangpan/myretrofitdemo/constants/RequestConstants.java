package com.example.zhangpan.myretrofitdemo.constants;

import android.util.Base64;

/**
 * Created by zhangpan on 16/8/24.
 */
public class RequestConstants {
    public final static String ENDPOINT = "https://api.github.com/";
    public final static String credentials = "user1"+":"+"user1password";
    public final static String auth_string =
            "Basic "+ Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
}

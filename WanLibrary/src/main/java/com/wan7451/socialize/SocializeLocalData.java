package com.wan7451.socialize;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2015/9/24.
 */
public class SocializeLocalData {

    public static SharedPreferences preferences;


    public static void init(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences("DML", Context.MODE_PRIVATE);
    }

    public static void saveSinaData(Context context, ThridLogin sina) {
        init(context);
        preferences.edit().putString("sina_data", JSONObject.toJSONString(sina)).commit();
    }

    public static ThridLogin getSinaData(Context context) {
        init(context);
        String data = preferences.getString("sina_data", "");
        if (TextUtils.isEmpty(data)) {
            return null;
        } else {
            return JSONObject.parseObject(data, ThridLogin.class);
        }
    }

    public static void saveWeiXinData(Context context, ThridLogin weixin) {
        init(context);
        preferences.edit().putString("weixin_data", JSONObject.toJSONString(weixin)).commit();
    }

    public static ThridLogin getWeiXinData(Context context) {
        init(context);
        String data = preferences.getString("weixin_data", "");
        if (TextUtils.isEmpty(data)) {
            return null;
        } else {
            return JSONObject.parseObject(data, ThridLogin.class);
        }
    }


    public static void saveQQData(Context context, ThridLogin weixin) {
        init(context);
        preferences.edit().putString("qq_data", JSONObject.toJSONString(weixin)).commit();
    }

    public static ThridLogin getQQData(Context context) {
        init(context);
        String data = preferences.getString("qq_data", "");
        if (TextUtils.isEmpty(data)) {
            return null;
        } else {
            return JSONObject.parseObject(data, ThridLogin.class);
        }
    }

    public static ThridLogin getDouBanData(Context context) {
        init(context);
        String data = preferences.getString("douban_data", "");
        if (TextUtils.isEmpty(data)) {
            return null;
        } else {
            return JSONObject.parseObject(data, ThridLogin.class);
        }

    }

    public static void saveDouBanData(Context context, ThridLogin data) {
        init(context);
        preferences.edit().putString("douban_data", JSONObject.toJSONString(data)).commit();
    }
}

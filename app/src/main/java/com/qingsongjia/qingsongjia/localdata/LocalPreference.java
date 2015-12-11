package com.qingsongjia.qingsongjia.localdata;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.bean.CityData;
import com.qingsongjia.qingsongjia.bean.KeMu;
import com.qingsongjia.qingsongjia.bean.UserData;
import com.qingsongjia.qingsongjia.bean.TiKu;
import com.qingsongjia.qingsongjia.bean.User;
import com.wan7451.base.WanActivity;

/**
 * 本地缓存数据
 */
public class LocalPreference {

    private static SharedPreferences spref;

    public static void getPreference(Context context) {
        if (spref == null)
            spref = context.getSharedPreferences("qsj", Context.MODE_PRIVATE);
    }

    public static boolean isFirstUse(Context context) {
        getPreference(context);
        boolean isFirstUse = spref.getBoolean("isFirstUse", true);
        if (isFirstUse) {
            spref.edit().putBoolean("isFirstUse", false).commit();
        }
        return isFirstUse;
    }


    /**
     * 保存当前科目
     */
    public static void setCurrentKemu(Context context, KeMu kemu) {
        getPreference(context);
        spref.edit().putString("currentKeMu", kemu.getValue()).commit();
    }

    /**
     * 保存当前科目
     */
    public static KeMu getCurrentKemu(Context context) {
        getPreference(context);
        String kemu = spref.getString("currentKeMu", "");
        switch (kemu) {
            case "kemu1":
                return KeMu.KEMU1;
            case "kemu2":
                return KeMu.KEMU2;
            case "kemu3":
                return KeMu.KEMU3;
            case "kemu4":
                return KeMu.KEMU4;
        }
        return KeMu.KEMU1;
    }


    /**
     * 保存当前题库
     */
    public static void setCurrentTiKu(Context context, TiKu tiku) {
        getPreference(context);
        spref.edit().putString("currentTiKu", tiku.getValue()).commit();
    }

    /**
     * 保存当前题库
     */
    public static TiKu getCurrentTiKu(Context context) {
        getPreference(context);
        String tiku = spref.getString("currentTiKu", "");
        switch (tiku) {
            case "xiaoche":
                return TiKu.XiaoChe;
            case "houche":
                return TiKu.HuoChe;
            case "keche":
                return TiKu.KeChe;
            case "motoche":
                return TiKu.MotoChe;
            case "jiaolianyuan":
                return TiKu.JiaoLianYuan;
            case "huoyun":
                return TiKu.HuoYun;
            case "weixianpin":
                return TiKu.WeiXianPin;
            case "keyun":
                return TiKu.KeYun;
            case "chuzuche":
                return TiKu.ChuZuChe;
        }
        return TiKu.XiaoChe;
    }


    /**
     * 保存当前用户数据
     */
    public static void saveCurrentUser(Context context, String user) {
        getPreference(context);
        spref.edit().putString("currentUser", user).commit();
    }

    public static User getCurrentUser(Context context) {
        getPreference(context);
        String curr = spref.getString("currentUser", "{}");
        return JSON.parseObject(curr, User.class);
    }

    public static void saveCurrentUserData(Context context, String data) {
        getPreference(context);
        spref.edit().putString("currentUserData", data).commit();
    }

    public static UserData getCurrentUserData(Context context) {
        getPreference(context);
        String curr = spref.getString("currentUserData", "{}");
        return JSON.parseObject(curr, UserData.class);
    }

    public static void clearData(Context context) {
        getPreference(context);
        spref.edit().clear().commit();
    }


    public static void savaTopImgPath(Context context, String path) {
        getPreference(context);
        spref.edit().putString("imgPath", path).commit();
    }

    public static String getTopImagePath(Context context) {
        getPreference(context);
        return spref.getString("imgPath", "http://pic.baike.soso.com/p/20120418/20120418170004-227985725.jpg");
    }


    /**
     * 保存当前的区域地址
     *
     * @param context
     * @param cityData
     */
    public static void saveCurrentAreaData(WanActivity context, CityData cityData) {
        getPreference(context);
        spref.edit().putString("area", JSONObject.toJSONString(cityData)).commit();
    }

    /**
     * 保存当前的城市地址
     *
     * @param context
     * @param cityData
     */
    public static void saveCurrentCityData(WanActivity context, CityData cityData) {
        getPreference(context);
        spref.edit().putString("city", JSONObject.toJSONString(cityData)).commit();
    }

    /**
     * 获得当前的城市地址
     *
     * @param context
     * @return
     */
    public static CityData getCurrentCityData(Context context) {
        getPreference(context);
        String city = spref.getString("city", "{}");
        return JSONObject.parseObject(city, CityData.class);
    }

    /**
     * 获得当前的区域地址
     *
     * @return
     */
    public static CityData getCurrentAreaData(Context context) {
        getPreference(context);
        String city = spref.getString("area", "{}");
        return JSONObject.parseObject(city, CityData.class);
    }
}

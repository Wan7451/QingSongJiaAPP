package com.qingsongjia.qingsongjia.localdata;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.qingsongjia.qingsongjia.bean.KeMu;
import com.qingsongjia.qingsongjia.bean.UserData;
import com.qingsongjia.qingsongjia.bean.TiKu;
import com.qingsongjia.qingsongjia.bean.User;

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
        return JSON.parseObject(curr,User.class);
    }

    public static void saveCurrentUserData(Context context,String data) {
        getPreference(context);
        spref.edit().putString("currentUserData", data).commit();
    }

    public static UserData getCurrentUserData(Context context) {
        getPreference(context);
        String curr = spref.getString("currentUserData", "{}");
        return JSON.parseObject(curr,UserData.class);
    }
}

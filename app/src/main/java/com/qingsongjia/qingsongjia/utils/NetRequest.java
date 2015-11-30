package com.qingsongjia.qingsongjia.utils;

import android.content.Context;

import com.wan7451.base.WanActivity;

import java.util.HashMap;

/**
 * 所有的网络请求
 * Created by wanggang on 15/11/30.
 */
public class NetRequest {


    /**
     * 注册
     *
     * @param dri_pass_word       密码
     * @param dri_tel             电话号码
     * @param dri_invitation_code 验证码
     */
    public static void register(Context context,
                                String dri_pass_word,
                                String dri_tel,
                                String dri_invitation_code,
                                NetUtils.NetUtilsHandler handler) {

        HashMap<String, String> params = new HashMap<>();
        params.put("dri_pass_word", dri_pass_word);
        params.put("dri_tel", dri_tel);
        params.put("dri_invitation_code", dri_invitation_code);

        NetUtils.baseRequest(context, "save", params, true, handler);
    }

    /**
     * 获取验证码
     */
    public static void getVerificationForPwd(Context context,
                                             String dri_tel,
                                NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_tel", dri_tel);
        NetUtils.baseRequest(context, "driappconsultclientWeb/getVerificationForPwd", params, true, handler);
    }

    /**
     * 驾校信息
     *
     * @param campusId
     */
    public static void loadCampusState(Context context,
                                       String campusId,
                                       NetUtils.NetUtilsHandler handler) {

        HashMap<String, String> params = new HashMap<>();
        params.put("campusId", campusId);
        NetUtils.baseRequest(context, "driappcommentweb/loadCampusState", params, true, handler);
    }



}

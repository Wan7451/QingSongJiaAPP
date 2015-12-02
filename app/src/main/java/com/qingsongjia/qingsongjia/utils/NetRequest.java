package com.qingsongjia.qingsongjia.utils;

import android.content.Context;

import com.qingsongjia.qingsongjia.localdata.LocalPreference;
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
     * @param dri_invitation_code 邀请码
     */
    public static void register(Context context,
                                String dri_tel,
                                String dri_pass_word,
                                String dri_invitation_code,
                                NetUtils.NetUtilsHandler handler) {

        HashMap<String, String> params = new HashMap<>();
        params.put("dri_pass_word", dri_pass_word);
        params.put("dri_tel", dri_tel);
        params.put("dri_invitation_code", dri_invitation_code);

        NetUtils.baseRequest(context, "driappconsultclientWeb/save", params, true, handler);
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


    /**
     * 登陆
     *
     * @param tel       手机
     * @param pass_word 密码
     */
    public static void loginLogin(WanActivity context, String tel, String pass_word, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_tel", tel);
        params.put("dri_pass_word", pass_word);
        NetUtils.baseRequest(context, "driappconsultclientWeb/dologin", params, true, handler);

    }

    /**
     * 修改密码
     *
     * @param pasd 密码
     */
    public static void changePasd(WanActivity context, String pasd, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", LocalPreference.getCurrentUser(context).getId() + "");
        params.put("dri_pass_word", pasd);
        NetUtils.baseRequest(context, "driappconsultclientWeb/changePwd", params, true, handler);

    }

    /**
     * 获取教练列表
     *
     * @param context
     * @param handler
     */
    public static void queryForCoursePlanList(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        NetUtils.baseRequest(context, "driappcorvpracticWeb/queryForCoursePlanList", params, false, handler);

    }

    /**
     * 获取课程安排
     *
     * @param id
     */
    public static void loadCoursePlanByCoursePlanId(WanActivity context, String id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        NetUtils.baseRequest(context, "driappcorvpracticWeb/loadCoursePlanByCoursePlanId", params, true, handler);
    }
}

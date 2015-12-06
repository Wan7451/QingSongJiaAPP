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
    public static void queryForCoursePlanList(WanActivity context, String time, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_dt_str", time);
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

    public static void sendInuiryTraining(WanActivity context, String dri_plan, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_student_id", LocalPreference.getCurrentUser(context).getId() + "");
        params.put("dri_plan", dri_plan);
        NetUtils.baseRequest(context, "driappcorvpracticWeb/save", params, true, handler);

    }

    public static void loadMyData(Context context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappconsultclientWeb/load", params, false, handler);

    }

    public static void loadMyTeacher(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", LocalPreference.getCurrentUserData(context).getDri_coach_id() + "");
        NetUtils.baseRequest(context, "driappcoach/load", params, true, handler);

    }

    /**
     * 我的约练
     *
     * @param context
     * @param handler
     */
    public static void getMyYueLian(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_student_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappcorvpracticWeb/queryForList", params, true, handler);

    }

    public static void getMyYueKao(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("Id", "127");
        params.put("dri_student_id", LocalPreference.getCurrentUser(context).getId() + "");
//        params.put("Id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driappcorvexamweb/ownerQuery", params, false, handler);
//        NetUtils.baseRequest(context, "driappcorvexamweb/load", params, false, handler);

    }

    public static void yuekao(WanActivity context,
                              String dri_dt,
                              String dri_tm,
                              String dri_sub_nm,
                              NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_student_id", LocalPreference.getCurrentUser(context).getId() + "");
        params.put("dri_dt_str", dri_dt);
        params.put("dri_tm", dri_tm);
        params.put("dri_sub_nm", dri_sub_nm);
        NetUtils.baseRequest(context, "driappcorvexamweb/save", params, false, handler);

    }

    public static void pushOrder(WanActivity context,
                                 String upDate,
                                 String price,
                                 String type,
                                 String other,
                                 NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_student_id", LocalPreference.getCurrentUser(context).getId() + "");
        params.put("meetingDate_str", upDate);
        params.put("dri_price", price);
        params.put("dri_partner_type", type);
        params.put("dri_comments", other);
        NetUtils.baseRequest(context, "driapppartnerTrain/save", params, false, handler);

    }

    /**
     * 教练确认约练
     *
     * @param id
     */
    public static void yxconfirm(WanActivity context, String id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        NetUtils.baseRequest(context, "driappcorvpracticWeb/confrim", params, false, handler);


    }

    /**
     * 学员是否学习
     * @param id
     */
    public static void studentCome(WanActivity context, String id, String dri_state, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("dri_state", dri_state);
        NetUtils.baseRequest(context, "driappcorvpracticWeb/edit", params, false, handler);
    }

    /**
     * 学员学习评价
     * @param id
     * @param mark
     */
    public static void markStudent(WanActivity context, String id, String mark, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("dri_remarks", mark);
        NetUtils.baseRequest(context, "driappcorvpracticWeb/edit", params, false, handler);

    }

    /**
     * 获得我的优惠劵信息
     * @param context
     * @param handler
     */
    public static void getMyMessage(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driappmypurse/load", params, false, handler);

    }

    /**
     * 获取我的积分
     */
    public static void getMyJiFen(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driappmypurse/queryIntegeralForList", params, false, handler);

    }

    /**
     * 获取我的余额
     * @param context
     * @param handler
     */
    public static void getMyYuE(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driappmypurse/queryMoneyForList", params, false, handler);

    }

    /**
     * 获取陪练信息
     * @param context
     * @param handler
     */
    public static void getPeiLianList(Context context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("user_id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driapppartnerTrain/queryForList", params, false, handler);

    }
}

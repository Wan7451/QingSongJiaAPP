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
        params.put("dri_coach_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappcorvpracticWeb/queryForList", params, false, handler);

    }

    public static void getMyYueKao(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("Id", "127");
        params.put("dri_student_id", LocalPreference.getCurrentUser(context).getId() + "");
//        params.put("Id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driappcorvexamweb/queryForList", params, false, handler);
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
                                 String time,
                                 String price,
                                 String type,
                                 String other,
                                 NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_user_id", LocalPreference.getCurrentUser(context).getId() + "");
        params.put("meetingDate_str", upDate);
        params.put("meetingTime", time);
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
     *
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
     *
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
     *
     * @param context
     * @param handler
     */
    public static void getMyMessage(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappmypurse/load", params, false, handler);

    }

    /**
     * 获取我的积分
     */
    public static void getMyJiFen(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappmypurse/queryIntegeralForList", params, false, handler);

    }

    /**
     * 获取我的余额
     *
     * @param context
     * @param handler
     */
    public static void getMyYuE(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappmypurse/queryMoneyForList", params, false, handler);

    }

    /**
     * 获取陪练信息
     *
     * @param context
     * @param handler
     */
    public static void getPeiLianList(Context context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("user_id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driapppartnerTrain/queryForList", params, false, handler);

    }

    /**
     * 预约陪练
     *
     * @param context
     * @param id
     * @param handler
     */
    public static void inquiryPeiLian(WanActivity context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id + "");
        params.put("dri_coach_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driapppartnerTrain/gradList", params, false, handler);

    }

    /**
     * 我的陪练
     */
    public static void loadMyPenLian(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_coach_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driapppartnerTrain/queryForList", params, false, handler);

    }

    /**
     * 我的学员
     *
     * @param context
     * @param handler
     */
    public static void loadMyStudent(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_coach_id", "171");
//        params.put("dri_coach_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappconsultclientWeb/queryForList", params, false, handler);

    }

    /**
     * 教练  我的陪练列表
     *
     * @param context
     * @param handler
     */
    public static void loadMyPeiLian(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("dri_user_id", "171");
        params.put("dri_user_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driapppartnerTrain/queryForList", params, false, handler);


    }

    /**
     * 获取我的优惠劵
     *
     * @param context
     * @param handler
     */
    public static void loadMyYouHuiJuan(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("dri_user_id", "171");
        params.put("dri_user_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappcoupon/queryForList", params, false, handler);

    }

    /**
     * 提现
     *
     * @param context
     * @param card
     * @param name
     * @param charge
     * @param handler
     */
    public static void tixian(WanActivity context, String card, String name, String charge, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("dri_user_id", "171");
        params.put("user_id", LocalPreference.getCurrentUser(context).getId() + "");
        params.put("worth", charge);
        params.put("card_num", card);
        params.put("opr", name);
        params.put("user_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappcoupon/queryForList", params, false, handler);
    }

    public static void downTopPicture(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        NetUtils.baseRequest(context, "driapptoppicture/getTopPicture", params, false, handler);
    }

    /**
     * 驾校列表
     */
    public static void getSchoolList(Context context,
                                     String cityCode,
                                     int money,
                                     int care,
                                     int praise,
                                     NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_city_code_id", cityCode);
        params.put("dri_money", money + "");
        params.put("dri_care", care + "");
        params.put("dri_praise", praise + "");
        NetUtils.baseRequest(context, "driapppraiseweb/queryForList", params, false, handler);

    }

    /**
     * 搜索驾校
     *
     * @param search
     */
    public static void searchSchoolList(Context context, String search, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("campusName", string2Unicode(search));
        NetUtils.baseRequest(context, "driapppraiseweb/queryForList", params, false, handler);
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * 驾校详情
     *
     * @param id
     */
    public static void loadSchoolDeatail(WanActivity context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("campusId", id + "");
        NetUtils.baseRequest(context, "driappcommentweb/loadCampusState", params, false, handler);

    }
}

package com.qingsongjia.qingsongjia.utils;

import android.content.Context;

import com.qingsongjia.qingsongjia.activity.SplashActivity;
import com.qingsongjia.qingsongjia.bean.User;
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

    public static void loadNewMyData(Context context, String tel, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_tel", tel);
        NetUtils.baseRequest(context, "driappconsultclientWeb/loadUserByUnm", params, false, handler);

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
        NetUtils.baseRequest(context, "driappcorvpracticWeb/queryForList", params, false, handler);

    }

    /**
     * 我的约练
     *
     * @param context
     * @param handler
     */
    public static void getTeacherYueLian(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_coach_id", LocalPreference.getCurrentUser(context).getId() + "");
        NetUtils.baseRequest(context, "driappcorvpracticWeb/queryForList", params, false, handler);

    }

    public static void getTeacherYueKao(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("Id", "127");
        params.put("dri_coach_id", LocalPreference.getCurrentUser(context).getId() + "");
//        params.put("Id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driappcorvexamweb/ownerQuery", params, false, handler);
//        NetUtils.baseRequest(context, "driappcorvexamweb/ownerQuery", params, false, handler);

    }

    public static void getMyYueKao(WanActivity context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("Id", "127");
        params.put("dri_student_id", LocalPreference.getCurrentUser(context).getId() + "");
//        params.put("Id", LocalPreference.getCurrentUser(context).getId()+"");
        NetUtils.baseRequest(context, "driappcorvexamweb/ownerQuery", params, false, handler);
//        NetUtils.baseRequest(context, "driappcorvexamweb/ownerQuery", params, false, handler);

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
        params.put("dri_student_remark", UnicodeUtil.stringToUnicode(mark));
        NetUtils.baseRequest(context, "driappcorvpracticWeb/edit", params, false, handler);
    }


    /**
     * 学员学习评价
     *
     * @param id
     * @param mark
     */
    public static void markTeacher(WanActivity context, String id, String mark, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("dri_remark", UnicodeUtil.stringToUnicode(mark));
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
        params.put("dri_state", "1");
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
        params.put("dri_coach_id", LocalPreference.getCurrentUser(context).getId() + "");
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
        params.put("opr", UnicodeUtil.stringToUnicode(name));

        NetUtils.baseRequest(context, "driappmypurse/saveMoney", params, false, handler);


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
        params.put("user_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        NetUtils.baseRequest(context, "driapppraiseweb/queryForList", params, false, handler);

    }

    /**
     * 搜索驾校
     *
     * @param search
     */
    public static void searchSchoolList(Context context, String search, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("campusName", UnicodeUtil.stringToUnicode(search));
        NetUtils.baseRequest(context, "driapppraiseweb/queryForList", params, false, handler);
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

    /**
     * 陪练投诉
     *
     * @param context
     * @param id
     * @param con
     * @param handler
     */
    public static void peilianTouSu(WanActivity context, int id, String con, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        User user = LocalPreference.getCurrentUser(context);
        params.put("dri_user_id", user.getId() + "");
        params.put("dri_type", user.getDri_type());
        params.put("dri_partner_id", id + "");
        params.put("dri_complaint_content", UnicodeUtil.stringToUnicode(con));
        NetUtils.baseRequest(context, "driapppartnerTrain/complaint", params, false, handler);


    }

    /**
     * 教练端  确认学员陪练
     *
     * @param context
     * @param handler
     */
    public static void queRenPeiLian(WanActivity context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id + "");
        params.put("status", "3");
        NetUtils.baseRequest(context, "driapppartnerTrain/changeState", params, false, handler);

    }

    public static void peijiapinglui(WanActivity context, int id, String remark, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id + "");
        params.put("dri_remark", UnicodeUtil.stringToUnicode(remark));
        NetUtils.baseRequest(context, "driapppartnerTrain/remark", params, false, handler);
    }

    /**
     * 驾校点赞
     *
     * @param context
     * @param campus_id
     * @param handler
     */
    public static void schoolZan(Context context, int campus_id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        User user = LocalPreference.getCurrentUser(context);
        params.put("user_id", user.getId() + "");
        params.put("dri_praise", "1");
        params.put("campusId", campus_id + "");
        NetUtils.baseRequest(context, "driapppraiseweb/save", params, false, handler);

    }

    /**
     * 驾校取消点赞
     *
     * @param context
     * @param campus_id
     * @param handler
     */
    public static void schoolCancelZan(Context context, int campus_id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        User user = LocalPreference.getCurrentUser(context);
        params.put("user_id", user.getId() + "");
        params.put("campusId", campus_id + "");
        NetUtils.baseRequest(context, "driapppraiseweb/offpraise", params, false, handler);
    }

    /**
     * 获得驾校评分
     *
     * @param context
     * @param campus_id
     */
    public static void getSchoolScores(WanActivity context, int campus_id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        User user = LocalPreference.getCurrentUser(context);
//        params.put("user_id", user.getId() + "");
        params.put("campusId", campus_id + "");
        NetUtils.baseRequest(context, "driappcommentweb/loadCampusGrade", params, true, handler);

    }

    public static void loadAllSchoolEvaluate(Context context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
//        User user = LocalPreference.getCurrentUser(context);
//        params.put("user_id", user.getId() + "");
        params.put("campusId", id + "");
//        params.put("campusId", 250+"");
        NetUtils.baseRequest(context, "driappcommentweb/queryForList", params, false, handler);

    }

    public static void getSplashImage(Context context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        NetUtils.baseRequest(context, "driappfirstimage/getFirstImage", params, false, handler);

    }

    public static void getTopPictur(Context context, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        NetUtils.baseRequest(context, "driapptoppicture/getTopPicture", params, false, handler);
    }

    /**
     * 学员修改个人信息
     *
     * @param context
     * @param dri_file_path
     * @param id
     * @param dri_campus_id
     * @param dri_coach_id
     * @param dri_coach_nm
     * @param dri_nm
     * @param handler
     */
    public static void saveStudent(Context context,
                                   String dri_file_path,
                                   String id,
                                   Integer dri_campus_id,
                                   Integer dri_coach_id,
                                   String dri_coach_nm,
                                   String dri_nm,
                                   NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("dri_file_path", dri_file_path);
        params.put("dri_campus_id", dri_campus_id + "");
        params.put("dri_coach_id", dri_coach_id + "");
        params.put("dri_coach_nm", UnicodeUtil.stringToUnicode(dri_coach_nm));
        params.put("dri_nm", UnicodeUtil.stringToUnicode(dri_nm));
        NetUtils.baseRequest(context, "driappconsultclientWeb/EduUpdate", params, true, handler);
    }

    /**
     * 教练修改个人信息
     *
     * @param context
     * @param dri_nm
     * @param dri_file_path
     * @param dri_campus_id
     * @param id
     * @param handler
     */
    public static void saveTeacher(Context context,
                                   String dri_nm,
                                   String dri_file_path,
                                   int dri_campus_id,
                                   int id, NetUtils.NetUtilsHandler handler) {

        HashMap<String, String> params = new HashMap<>();
        params.put("dri_file_path", dri_file_path);
        params.put("dri_nm", UnicodeUtil.stringToUnicode(dri_nm));
        params.put("dri_campus_id", dri_campus_id + "");
        params.put("id", id + "");
        NetUtils.baseRequest(context, "driappconsultclientWeb/CoathUpdate", params, true, handler);

    }

    public static void getVerification(Context context, String phone, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("telNum", phone);
        NetUtils.baseRequest(context, "driappconsultclientWeb/getNum", params, true, handler);
    }

    public static void loadCurrentSchoolEvaluate(Context context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("campusId", id + "");
        params.put("user_campus_ids", id + "");
        NetUtils.baseRequest(context, "driappcommentweb/queryForList", params, false, handler);

    }

    public static void evaluateSchool(WanActivity context,
                                      int dri_campus_id,
                                      int place,
                                      int time,
                                      int pass,
                                      String eval,
                                      NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_remark", UnicodeUtil.stringToUnicode(eval));
        params.put("dri_place", place + "");
        params.put("dri_time", time + "");
        params.put("dri_pass", pass + "");
        params.put("campusId", dri_campus_id + "");
        params.put("create_id",
                LocalPreference.getCurrentUserData(context)
                        .getDid() + "");
        NetUtils.baseRequest(context, "driappcommentweb/save", params, true, handler);

    }

    public static void loadExchange(Context context, String type, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_type", UnicodeUtil.stringToUnicode(type));
        params.put("user_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        NetUtils.baseRequest(context, "driappexchangeWeb/queryForList", params, false, handler);

    }

    public static void signUp(Context context,
                              int dri_goal,
                              int dri_car_type,
                              String name,
                              String phone,
                              String dri_remark,
                              int dri_entry_fee,
                              NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", LocalPreference.getCurrentUserData(context).getDid() + "");
        params.put("dri_goal", dri_goal + "");
        params.put("dri_car_type", dri_car_type + "");
        params.put("dri_bm_tel", phone);
        params.put("dri_nm", UnicodeUtil.stringToUnicode(name));
        params.put("dri_remark", UnicodeUtil.stringToUnicode(dri_remark));
        params.put("dri_entry_fee", dri_entry_fee + "");
        NetUtils.baseRequest(context, "driappconsultclientWeb/enrolled", params, true, handler);

    }

    public static void pushExchange(WanActivity context, String dri_type, String dri_text, String dri_image_url, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("create_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        params.put("dri_type", UnicodeUtil.stringToUnicode(dri_type));
        params.put("dri_text", UnicodeUtil.stringToUnicode(dri_text));
        params.put("dri_image_url", dri_image_url);
        NetUtils.baseRequest(context, "driappexchangeWeb/save", params, true, handler);

    }

    public static void loadExchangeDetail(Context context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id + "");
        NetUtils.baseRequest(context, "driappexchangeWeb/load", params, true, handler);

    }

    public static void loadExchangeComment(Context context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_reply_id", id + "");
        NetUtils.baseRequest(context, "driappexchangeWeb/loadReply", params, true, handler);

    }

    public static void zanExchange(Context context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("ex_id", id + "");
        params.put("user_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        NetUtils.baseRequest(context, "driappExpraiseweb/save", params, false, handler);
    }

    public static void offZanExchange(Context context, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("ex_id", id + "");
        params.put("user_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        NetUtils.baseRequest(context, "driappExpraiseweb/offpraise", params, false, handler);
    }

    public static void replyExchange(Context context, String upText, int id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_text", UnicodeUtil.stringToUnicode(upText));
        params.put("dri_reply_id", id + "");
        params.put("dri_reply_type", "1");
        params.put("create_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        NetUtils.baseRequest(context, "driappexchangeWeb/saveReply", params, false, handler);
    }

    public static void replyExchangeReply(WanActivity context, String upText, int replyId, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dri_text", UnicodeUtil.stringToUnicode(upText));
        params.put("dri_reply_id", replyId + "");
        params.put("dri_reply_type", "2");
        params.put("create_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        NetUtils.baseRequest(context, "driappexchangeWeb/saveReply", params, false, handler);

    }

    /**
     *
     * @param context
     *   关注驾校   >0关注  ==0取消关注
     * @param campus_id
     * @param handler
     */
    public static void attentionSchool(Context context, int campus_id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("campusId", campus_id + "");
        params.put("dri_care", 1+"");
        params.put("user_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        NetUtils.baseRequest(context, "driapppraiseweb/save", params, true, handler);

    }

    /**
     *  取消关注
     * @param context
     * @param campus_id
     * @param handler
     */
    public static void attentionOffSchool(Context context, int campus_id, NetUtils.NetUtilsHandler handler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("campusId", campus_id + "");
        params.put("user_id", LocalPreference.getCurrentUserData(context).getDid() + "");
        NetUtils.baseRequest(context, "driapppraiseweb/offcare", params, true, handler);

    }
}

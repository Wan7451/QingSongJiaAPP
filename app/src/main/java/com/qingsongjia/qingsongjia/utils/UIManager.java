package com.qingsongjia.qingsongjia.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.qingsongjia.qingsongjia.activity.LoginActivity;
import com.qingsongjia.qingsongjia.activity.MainActivity;
import com.qingsongjia.qingsongjia.activity.RegisterActivity;
import com.qingsongjia.qingsongjia.activity.SetPasswordActivity;
import com.qingsongjia.qingsongjia.activity.VerifyPhoneActivity;
import com.qingsongjia.qingsongjia.bean.CityData;
import com.qingsongjia.qingsongjia.bean.MyPeiLian;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.driverexam.AnalogyExamActivity;
import com.qingsongjia.qingsongjia.driverexam.AnalogyTestActivity;
import com.qingsongjia.qingsongjia.driverexam.ExamDetailActivity;
import com.qingsongjia.qingsongjia.driverexam.ExamTestActivity;
import com.qingsongjia.qingsongjia.driverschool.AreaListActivity;
import com.qingsongjia.qingsongjia.driverschool.CityListActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolDetailActivity;
import com.qingsongjia.qingsongjia.yuekao.InquiryExamActivity;
import com.qingsongjia.qingsongjia.yuexun.InquiryTrainingActivity;
import com.qingsongjia.qingsongjia.driverexam.ItemListActivity;
import com.qingsongjia.qingsongjia.driverexam.JiaoTongBiaoZhiActivity;
import com.qingsongjia.qingsongjia.driverexam.JiaoTongBiaoZhiGridActivity;
import com.qingsongjia.qingsongjia.driverexam.TeacherDetailActivity;
import com.qingsongjia.qingsongjia.driverexam.TeacherListActivity;
import com.qingsongjia.qingsongjia.driverexam.WebActivity;
import com.qingsongjia.qingsongjia.driverexam.ZhangJieLianXiActivity;
import com.qingsongjia.qingsongjia.driverexam.ZhuanIXiangLianXiActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolSearchActivity;
import com.qingsongjia.qingsongjia.plxc.InquirySendActivity;
import com.qingsongjia.qingsongjia.teacher.InquiryConfirmActivity;
import com.qingsongjia.qingsongjia.teacher.MyStudentActivity;
import com.qingsongjia.qingsongjia.teacher.MyStudentYKActivity;
import com.qingsongjia.qingsongjia.teacher.MyStudentYXActivity;
import com.qingsongjia.qingsongjia.teacher.PeiLianXingChengActivity;
import com.qingsongjia.qingsongjia.teacher.PushOrderActivity;
import com.qingsongjia.qingsongjia.teacher.YXConfirmActivity;
import com.qingsongjia.qingsongjia.teacher.YXEvaluateActivity;
import com.qingsongjia.qingsongjia.user.ChangeExamLibsActivity;
import com.qingsongjia.qingsongjia.yuekao.MyExamActivity;
import com.qingsongjia.qingsongjia.user.MyJiFeiActivity;
import com.qingsongjia.qingsongjia.user.MyMessageActivity;
import com.qingsongjia.qingsongjia.user.MyTeacherActivity;
import com.qingsongjia.qingsongjia.user.MyYouHuiJuanActivity;
import com.qingsongjia.qingsongjia.user.MyYuEActivity;
import com.qingsongjia.qingsongjia.plxc.PenLianActivity;
import com.qingsongjia.qingsongjia.user.SettingActivity;
import com.qingsongjia.qingsongjia.user.TiXianActivity;
import com.qingsongjia.qingsongjia.yuexun.MyTestActivity;
import com.qingsongjia.qingsongjia.yuexun.MyTestDetailActivity;
import com.wan7451.base.WanActivity;

import java.util.ArrayList;

/**
 * Created by wanggang on 15/11/17.
 */
public class UIManager {

    public static void startSchoolList(Context context) {
        Intent i = new Intent();
        i.setClass(context, SchoolSearchActivity.class);
        context.startActivity(i);
    }

    public static void startRegister(Context context) {
        Intent i = new Intent();
        i.setClass(context, RegisterActivity.class);
        context.startActivity(i);
    }

    public static void startVerify(Context context) {
        Intent i = new Intent();
        i.setClass(context, VerifyPhoneActivity.class);
        context.startActivity(i);
    }

    public static void startSetPassword(Context context) {
        Intent i = new Intent();
        i.setClass(context, SetPasswordActivity.class);
        context.startActivity(i);
    }


    /**
     * 打开练习详情
     *
     * @param context
     * @param type
     */
    public static void startExamDetail(Context context, String type) {
        Intent i = new Intent();
        i.setClass(context, ExamDetailActivity.class);
        i.putExtra("type", type);
        context.startActivity(i);
    }

    /**
     * 打开模拟考试
     *
     * @param context
     */
    public static void startAnalogyExam(Context context, int type) {
        Intent i = new Intent();
        i.setClass(context, AnalogyExamActivity.class);
        i.putExtra("type", type);
        context.startActivity(i);
    }

    /**
     * 打开预约考试
     *
     * @param context
     * @param inquiryTypeOne
     */
    public static void startInquiryExam(Context context, int inquiryTypeOne) {
        Intent i = new Intent();
        i.setClass(context, InquiryExamActivity.class);
        i.putExtra("type", inquiryTypeOne);
        context.startActivity(i);
    }

    public static void startAnalogyTest(Context context, int type) {
        Intent i = new Intent();
        i.setClass(context, AnalogyTestActivity.class);
        i.putExtra("type", type);
        context.startActivity(i);
    }

    public static void startExamTest(Context context, int type) {
        Intent i = new Intent();
        i.setClass(context, ExamTestActivity.class);
        i.putExtra("type", type);
        context.startActivity(i);
    }

    public static void startInquiryTraining(Context context) {
        Intent i = new Intent();
        i.setClass(context, InquiryTrainingActivity.class);
        context.startActivity(i);
    }

    public static void startTeacherList(Context context, String time) {
        Intent i = new Intent();
        i.setClass(context, TeacherListActivity.class);
        i.putExtra("time", time);
        context.startActivity(i);
    }

    public static void startTeacherDetail(Context context, String id) {
        Intent i = new Intent();
        i.setClass(context, TeacherDetailActivity.class);
        i.putExtra("id", id);
        context.startActivity(i);
    }

    public static void startMyTeacher(Context context) {
        Intent i = new Intent();
        i.setClass(context, MyTeacherActivity.class);
        context.startActivity(i);
    }

    public static void startMyMessage(Context context) {
        Intent i = new Intent();
        i.setClass(context, MyMessageActivity.class);
        context.startActivity(i);
    }

    public static void startMyJiFei(Context context, int integral) {
        Intent i = new Intent();
        i.setClass(context, MyJiFeiActivity.class);
        i.putExtra("jifen", integral);
        context.startActivity(i);
    }

    public static void startMyYuE(Context context, int money) {
        Intent i = new Intent();
        i.setClass(context, MyYuEActivity.class);
        i.putExtra("money", money);
        context.startActivity(i);
    }

    public static void startTiXian(Context context) {
        Intent i = new Intent();
        i.setClass(context, TiXianActivity.class);
        context.startActivity(i);
    }

    public static void startMyTest(Context context) {
        Intent i = new Intent();
        i.setClass(context, MyTestActivity.class);
        context.startActivity(i);
    }

    public static void startMyExam(Context context) {
        Intent i = new Intent();
        i.setClass(context, MyExamActivity.class);
        context.startActivity(i);
    }

    public static void startPenLian(Context context) {
        Intent i = new Intent();
        i.setClass(context, PenLianActivity.class);
        context.startActivity(i);
    }

    public static void startSetting(Context context) {
        Intent i = new Intent();
        i.setClass(context, SettingActivity.class);
        context.startActivity(i);
    }

    public static void startZhangJieTest(Context context) {
        Intent i = new Intent();
        i.setClass(context, ZhangJieLianXiActivity.class);
        context.startActivity(i);
    }

    public static void startZhuanXiangTest(Context context) {
        Intent i = new Intent();
        i.setClass(context, ZhuanIXiangLianXiActivity.class);
        context.startActivity(i);
    }

    public static void startWebView(Context context, String title, String url) {
        Intent i = new Intent();
        i.setClass(context, WebActivity.class);
        i.putExtra("url", url);
        i.putExtra("title", title);
        context.startActivity(i);
    }

    public static void startJiaoTongBiaoZhi(Context context) {
        Intent i = new Intent();
        i.setClass(context, JiaoTongBiaoZhiActivity.class);
        context.startActivity(i);
    }

    public static void startJiaoTongBiaoZhiGrid(Context context) {

        Intent i = new Intent();
        i.setClass(context, JiaoTongBiaoZhiGridActivity.class);
        context.startActivity(i);
    }

    public static void startItemList(Context context, int type) {
        Intent i = new Intent();
        i.setClass(context, ItemListActivity.class);
        i.putExtra("type", type);
        context.startActivity(i);
    }

    public static void startMyStudents(Context context) {
        Intent i = new Intent();
        i.setClass(context, MyStudentActivity.class);
        context.startActivity(i);
    }

    public static void startMyStudentYK(Context context) {
        Intent i = new Intent();
        i.setClass(context, MyStudentYKActivity.class);
        context.startActivity(i);
    }

    public static void startMyStudentYX(Context context) {
        Intent i = new Intent();
        i.setClass(context, MyStudentYXActivity.class);
        context.startActivity(i);
    }

    public static void startYXConfirm(WanActivity context, int id) {
        Intent i = new Intent();
        i.setClass(context, YXConfirmActivity.class);
        i.putExtra("id", id);
        context.startActivity(i);
    }

    public static void startYXEvaluate(WanActivity context, MyYueKao data) {
        Intent i = new Intent();
        i.setClass(context, YXEvaluateActivity.class);
        i.putExtra("data", data);
        context.startActivity(i);
    }

    public static void startMyPeiLianXingCheng(Context context) {
        Intent i = new Intent();
        i.setClass(context, PeiLianXingChengActivity.class);
        context.startActivity(i);
    }

    /**
     * 打开发布
     *
     * @param context
     */
    public static void startPushOrder(Context context) {
        Intent i = new Intent();
        i.setClass(context, PushOrderActivity.class);
        context.startActivity(i);

    }

    /**
     * 预约训练
     *
     * @param data
     */
    public static void startYueXunConfirm(WanActivity context, MyYueKao data) {
        Intent i = new Intent();
        i.setClass(context, YXConfirmActivity.class);
        i.putExtra("data", data);
        context.startActivity(i);
    }

    /**
     * 打开培训详情
     *
     * @param context
     * @param item
     */
    public static void startPenLianDetail(Context context, PeiLian item) {
        Intent i = new Intent();
        i.setClass(context, InquirySendActivity.class);
        i.putExtra("peilian", item);
        context.startActivity(i);
    }

    /**
     * 修改题库
     *
     * @param context
     * @param code
     */
    public static void startChangeExamLibsForReslut(Fragment context, int code) {
        Intent i = new Intent();
        i.setClass(context.getContext(), ChangeExamLibsActivity.class);
        context.startActivityForResult(i, code);
    }


    public static void startLogin(Context context) {
        Intent i = new Intent();
        i.setClass(context, LoginActivity.class);
        i.putExtra("isLogin", true);
        context.startActivity(i);
    }

    /**
     * 打开我的优惠劵列表
     *
     * @param context
     */
    public static void startMyYouHuiJuan(WanActivity context) {
        Intent i = new Intent();
        i.setClass(context.getContext(), MyYouHuiJuanActivity.class);
        context.startActivity(i);
    }

    /**
     * 确认 陪练预约
     *
     * @param context
     * @param data
     */
    public static void startInquiryConfirm(WanActivity context, MyPeiLian data) {
        Intent i = new Intent();
        i.setClass(context.getContext(), InquiryConfirmActivity.class);
        i.putExtra("data", data);
        context.startActivity(i);
    }

    /**
     * 打开我的练习
     *
     * @param context
     * @param data
     */
    public static void startMyTestDetail(WanActivity context, MyYueKao data) {
        Intent i = new Intent();
        i.setClass(context.getContext(), MyTestDetailActivity.class);
        i.putExtra("data", data);
        context.startActivity(i);
    }

    /**
     * 打开 区域选择
     *
     * @param context
     * @param s
     */
    public static void startAreaList(WanActivity context, ArrayList<CityData> s, CityData c) {
        Intent i = new Intent();
        i.setClass(context.getContext(), AreaListActivity.class);
        i.putExtra("data", s);
        i.putExtra("city", c);
        context.startActivity(i);
    }

    public static void startChoiceCity(Context context) {
        Intent i = new Intent();
        i.setClass(context, CityListActivity.class);
        context.startActivity(i);
    }


    public static void startSchoolSearch(Context context) {
        Intent i = new Intent();
        i.setClass(context, SchoolSearchActivity.class);
        context.startActivity(i);
    }

    public static void startSchoolDetail(Context context, int id) {
        Intent i = new Intent();
        i.setClass(context, SchoolDetailActivity.class);
        i.putExtra("id", id);
        context.startActivity(i);
    }
}

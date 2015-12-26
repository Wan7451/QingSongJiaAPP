package com.qingsongjia.qingsongjia.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.qingsongjia.qingsongjia.activity.LoginActivity;
import com.qingsongjia.qingsongjia.activity.MainActivity;
import com.qingsongjia.qingsongjia.activity.MapActivity;
import com.qingsongjia.qingsongjia.activity.RegisterActivity;
import com.qingsongjia.qingsongjia.activity.SetPasswordActivity;
import com.qingsongjia.qingsongjia.activity.VerifyPhoneActivity;
import com.qingsongjia.qingsongjia.bean.CityData;
import com.qingsongjia.qingsongjia.bean.MyPeiLian;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.bean.SchoolDetail;
import com.qingsongjia.qingsongjia.driverexam.AnalogyExamActivity;
import com.qingsongjia.qingsongjia.driverexam.AnalogyTestActivity;
import com.qingsongjia.qingsongjia.driverexam.ExamDetailActivity;
import com.qingsongjia.qingsongjia.driverexam.ExamTestActivity;
import com.qingsongjia.qingsongjia.driverexam.JiaoTongBiaoZhiListActivity;
import com.qingsongjia.qingsongjia.driverexam.WebListActivity;
import com.qingsongjia.qingsongjia.driverexam.XinShouShangLuActivity;
import com.qingsongjia.qingsongjia.driverexam.XinShouShangLuListActivity;
import com.qingsongjia.qingsongjia.driverschool.AreaListActivity;
import com.qingsongjia.qingsongjia.driverschool.BusRouteActivity;
import com.qingsongjia.qingsongjia.driverschool.CityListActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolDetailActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolEvaluateActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolImgsActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolListActivity;
import com.qingsongjia.qingsongjia.driverschool.SignUpActivity;
import com.qingsongjia.qingsongjia.exchange.ExchangeDetailActivity;
import com.qingsongjia.qingsongjia.exchange.ReplyActivity;
import com.qingsongjia.qingsongjia.plxc.AllSparringListActivity;
import com.qingsongjia.qingsongjia.plxc.T_SparringEvaluateActivity;
import com.qingsongjia.qingsongjia.plxc.T_SparringComplaintActivity;
import com.qingsongjia.qingsongjia.teacher.TeacherMessageActivity;
import com.qingsongjia.qingsongjia.user.StudentMessageActivity;
import com.qingsongjia.qingsongjia.yuekao.InquiryExamActivity;
import com.qingsongjia.qingsongjia.yuexun.S_InquiryTrainingActivity;
import com.qingsongjia.qingsongjia.driverexam.ItemListActivity;
import com.qingsongjia.qingsongjia.driverexam.JiaoTongBiaoZhiActivity;
import com.qingsongjia.qingsongjia.driverexam.JiaoTongBiaoZhiGridActivity;
import com.qingsongjia.qingsongjia.driverexam.TeacherDetailActivity;
import com.qingsongjia.qingsongjia.driverexam.TeacherListActivity;
import com.qingsongjia.qingsongjia.driverexam.WebActivity;
import com.qingsongjia.qingsongjia.driverexam.ZhangJieLianXiActivity;
import com.qingsongjia.qingsongjia.driverexam.ZhuanIXiangLianXiActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolSearchActivity;
import com.qingsongjia.qingsongjia.plxc.S_SparringInquiryActivity;
import com.qingsongjia.qingsongjia.plxc.T_SparringConfirmActivity;
import com.qingsongjia.qingsongjia.teacher.T_StudentListActivity;
import com.qingsongjia.qingsongjia.teacher.T_StudentExamListActivity;
import com.qingsongjia.qingsongjia.yuexun.T_TrainingListActivity;
import com.qingsongjia.qingsongjia.plxc.T_SparringListActivity;
import com.qingsongjia.qingsongjia.plxc.T_PushSparringActivity;
import com.qingsongjia.qingsongjia.yuexun.T_TrainingConfirmActivity;
import com.qingsongjia.qingsongjia.yuexun.T_TrainingEvaluateActivity;
import com.qingsongjia.qingsongjia.user.ChangeExamLibsActivity;
import com.qingsongjia.qingsongjia.yuekao.MyExamActivity;
import com.qingsongjia.qingsongjia.user.MyJiFeiActivity;
import com.qingsongjia.qingsongjia.user.MyMessageActivity;
import com.qingsongjia.qingsongjia.user.MyTeacherActivity;
import com.qingsongjia.qingsongjia.user.MyYouHuiJuanActivity;
import com.qingsongjia.qingsongjia.user.MyYuEActivity;
import com.qingsongjia.qingsongjia.plxc.S_SparringListActivity;
import com.qingsongjia.qingsongjia.user.SettingActivity;
import com.qingsongjia.qingsongjia.user.TiXianActivity;
import com.qingsongjia.qingsongjia.yuexun.S_TrainingEvaluateActivity;
import com.qingsongjia.qingsongjia.yuexun.S_TrainingListActivity;
import com.qingsongjia.qingsongjia.yuexun.S_TrainingDetailActivity;
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
    public static void startExamDetail(Context context,String title, String type) {
        Intent i = new Intent();
        i.setClass(context, ExamDetailActivity.class);
        i.putExtra("type", type);
        i.putExtra("title", title);
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
        i.setClass(context, S_InquiryTrainingActivity.class);
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
        i.setClass(context, S_TrainingListActivity.class);
        context.startActivity(i);
    }

    public static void startMyExam(Context context) {
        Intent i = new Intent();
        i.setClass(context, MyExamActivity.class);
        context.startActivity(i);
    }

    public static void startPenLian(Context context) {
        Intent i = new Intent();
        i.setClass(context, S_SparringListActivity.class);
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

    public static void startJiaoTongBiaoZhiGrid(Context context, String title, String path) {

        Intent i = new Intent();
        i.setClass(context, JiaoTongBiaoZhiGridActivity.class);
        i.putExtra("path",path);
        i.putExtra("title",title);
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
        i.setClass(context, T_StudentListActivity.class);
        context.startActivity(i);
    }

    public static void startMyStudentYK(Context context) {
        Intent i = new Intent();
        i.setClass(context, T_StudentExamListActivity.class);
        context.startActivity(i);
    }

    public static void startMyStudentYX(Context context) {
        Intent i = new Intent();
        i.setClass(context, T_TrainingListActivity.class);
        context.startActivity(i);
    }

    public static void startYXConfirm(WanActivity context, int id) {
        Intent i = new Intent();
        i.setClass(context, T_TrainingConfirmActivity.class);
        i.putExtra("id", id);
        context.startActivity(i);
    }

    public static void startYXEvaluate(WanActivity context, MyYueKao data) {
        Intent i = new Intent();
        i.setClass(context, T_TrainingEvaluateActivity.class);
        i.putExtra("data", data);
        context.startActivity(i);
    }

    public static void startMyPeiLianXingCheng(Context context) {
        Intent i = new Intent();
        i.setClass(context, T_SparringListActivity.class);
        context.startActivity(i);
    }

    /**
     * 打开发布
     *
     * @param context
     */
    public static void startPushOrder(Context context) {
        Intent i = new Intent();
        i.setClass(context, T_PushSparringActivity.class);
        context.startActivity(i);

    }

    /**
     * 预约训练
     *
     * @param data
     */
    public static void startYueXunConfirm(WanActivity context, MyYueKao data) {
        Intent i = new Intent();
        i.setClass(context, T_TrainingConfirmActivity.class);
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
        i.setClass(context, S_SparringInquiryActivity.class);
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
        i.putExtra("isGoBack", true);
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
        i.setClass(context.getContext(), T_SparringConfirmActivity.class);
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
        i.setClass(context.getContext(), S_TrainingDetailActivity.class);
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

    public static void startPeiLianList(Context context) {
        Intent i = new Intent();
        i.setClass(context, AllSparringListActivity.class);
        context.startActivity(i);
    }

    public static void startYueXunComnent(WanActivity context, MyYueKao data) {
        Intent i = new Intent();
        i.setClass(context, T_TrainingEvaluateActivity.class);
        i.putExtra("data",data);
        context.startActivity(i);
    }

    /**
     * 打开投诉
     * @param context
     */
    public static void startTouSuo(WanActivity context,int id) {
        Intent i = new Intent();
        i.setClass(context, T_SparringComplaintActivity.class);
        i.putExtra("id",id);
        context.startActivity(i);
    }

    public static void startStudentYXEval(WanActivity context, MyYueKao data) {
        Intent i = new Intent();
        i.setClass(context, S_TrainingEvaluateActivity.class);
        i.putExtra("data",data);
        context.startActivity(i);
    }

    public static void startJiaoTongBiaoZhiList(WanActivity context) {
        Intent i = new Intent();
        i.setClass(context, JiaoTongBiaoZhiListActivity.class);
        context.startActivity(i);
    }

    public static void startWebList(WanActivity context) {
        Intent i = new Intent();
        i.setClass(context, WebListActivity.class);
        context.startActivity(i);
    }

    public static void startXinShouShangLu(Context context) {
        Intent i = new Intent();
        i.setClass(context, XinShouShangLuActivity.class);
        context.startActivity(i);
    }

    public static void startXinShouShangLuList(WanActivity context, int type) {
        Intent i = new Intent();
        i.setClass(context, XinShouShangLuListActivity.class);
        i.putExtra("type",type);
        context.startActivity(i);
    }

    /**
     * 教练  陪练 打开评论
     * @param context
     * @param data
     */
    public static void startPeiLianPingJia(WanActivity context, PeiLian data) {
        Intent i=new Intent();
        i.setClass(context, T_SparringEvaluateActivity.class);
        i.putExtra("data",data);
        context.startActivity(i);
    }

    /**
     * 打开百度地图
     * @param context
     */
    public static void startMapView(WanActivity context, String title, String map_addr) {
        Intent i=new Intent();
        i.setClass(context, MapActivity.class);
        i.putExtra("title",title);
        i.putExtra("map_addr",map_addr);
        context.startActivity(i);
    }

    /**
     * 班车路线
     * @param context
     */
    public static void startBusRoute(WanActivity context, String routeLine) {
        Intent i=new Intent();
        i.setClass(context, BusRouteActivity.class);
        i.putExtra("routeLine",routeLine);
        context.startActivity(i);
    }

    public static void startSchoolImages(WanActivity context, String[] imgs) {
        Intent i=new Intent();
        i.setClass(context, SchoolImgsActivity.class);
        i.putExtra("imgs",imgs);
        context.startActivity(i);
    }

    public static void startSchoolEvaluate(WanActivity context, SchoolDetail data ) {
        Intent i=new Intent();
        i.setClass(context, SchoolEvaluateActivity.class);
        i.putExtra("data",data);
        context.startActivity(i);
    }

    public static void startAllSchoolList(Context context) {
        Intent i=new Intent();
        i.setClass(context, SchoolListActivity.class);
        context.startActivity(i);
    }

    public static void startStudentData(Context context) {
        Intent i=new Intent();
        i.setClass(context, StudentMessageActivity.class);
        context.startActivity(i);
    }

    public static void startSignUp(WanActivity context, int dri_campus_id) {
        Intent i=new Intent();
        i.setClass(context, SignUpActivity.class);
        i.putExtra("id",dri_campus_id);
        context.startActivity(i);
    }

    public static void startTeacherMessage(Context context) {
        Intent i=new Intent();
        i.setClass(context, TeacherMessageActivity.class);
        context.startActivity(i);
    }

    public static void startExchangeDeatil(Context context,int id) {
        Intent i=new Intent();
        i.setClass(context, ExchangeDetailActivity.class);
        i.putExtra("id",id);
        context.startActivity(i);
    }

    public static void startReply(Context context, int posotion) {
        Intent i=new Intent();
        i.setClass(context, ReplyActivity.class);
        i.putExtra("posotion",posotion);
        context.startActivity(i);
    }
}

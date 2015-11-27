package com.qingsongjia.qingsongjia.utils;

import android.content.Context;
import android.content.Intent;

import com.qingsongjia.qingsongjia.activity.RegisterActivity;
import com.qingsongjia.qingsongjia.activity.SetPasswordActivity;
import com.qingsongjia.qingsongjia.activity.VerifyPhoneActivity;
import com.qingsongjia.qingsongjia.driverexam.AnalogyExamActivity;
import com.qingsongjia.qingsongjia.driverexam.AnalogyTestActivity;
import com.qingsongjia.qingsongjia.driverexam.ExamDetailActivity;
import com.qingsongjia.qingsongjia.driverexam.ExamTestActivity;
import com.qingsongjia.qingsongjia.driverexam.ExamTestFragment;
import com.qingsongjia.qingsongjia.driverexam.InquiryExamActivity;
import com.qingsongjia.qingsongjia.driverexam.InquiryTrainingActivity;
import com.qingsongjia.qingsongjia.driverexam.TeacherDetailActivity;
import com.qingsongjia.qingsongjia.driverexam.TeacherListActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolListActivity;
import com.qingsongjia.qingsongjia.user.ChangeExamLibsActivity;
import com.wan7451.base.WanActivity;

/**
 * Created by wanggang on 15/11/17.
 */
public class UIManager {

    public static void startSchoolList(Context context){
        Intent i=new Intent();
        i.setClass(context, SchoolListActivity.class);
        context.startActivity(i);
    }

    public static void startRegister(Context context) {
        Intent i=new Intent();
        i.setClass(context, RegisterActivity.class);
        context.startActivity(i);
    }

    public static void startVerify(Context context) {
        Intent i=new Intent();
        i.setClass(context, VerifyPhoneActivity.class);
        context.startActivity(i);
    }

    public static void startSetPassword(Context context) {
        Intent i=new Intent();
        i.setClass(context, SetPasswordActivity.class);
        context.startActivity(i);
    }

    public static void startChangeExamLibs(Context context) {
        Intent i = new Intent();
        i.setClass(context, ChangeExamLibsActivity.class);
        context.startActivity(i);
    }

    /**
     * 打开练习详情
     * @param context
     * @param type
     */
    public static void startExamDetail(Context context, String type) {
        Intent i=new Intent();
        i.setClass(context, ExamDetailActivity.class);
        i.putExtra("type", type);
        context.startActivity(i);
    }

    /**
     * 打开模拟考试
     * @param context
     */
    public static void startAnalogyExam(Context context) {
        Intent i=new Intent();
        i.setClass(context, AnalogyExamActivity.class);
        context.startActivity(i);
    }

    /**
     * 打开预约考试
     * @param context
     * @param inquiryTypeOne
     */
    public static void startInquiryExam(Context context, int inquiryTypeOne) {
        Intent i=new Intent();
        i.setClass(context, InquiryExamActivity.class);
        i.putExtra("type", inquiryTypeOne);
        context.startActivity(i);
    }

    public static void startAnalogyTest(Context context) {
        Intent i=new Intent();
        i.setClass(context, AnalogyTestActivity.class);
        context.startActivity(i);
    }

    public static void startExamTest(Context context) {
        Intent i=new Intent();
        i.setClass(context, ExamTestActivity.class);
        context.startActivity(i);
    }

    public static void startInquiryTraining(Context context) {
        Intent i=new Intent();
        i.setClass(context, InquiryTrainingActivity.class);
        context.startActivity(i);
    }

    public static void startTeacherList(Context context) {
        Intent i=new Intent();
        i.setClass(context, TeacherListActivity.class);
        context.startActivity(i);
    }

    public static void startTeacherDetail(Context context) {
        Intent i=new Intent();
        i.setClass(context, TeacherDetailActivity.class);
        context.startActivity(i);
    }
}

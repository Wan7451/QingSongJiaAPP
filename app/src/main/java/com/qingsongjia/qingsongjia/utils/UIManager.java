package com.qingsongjia.qingsongjia.utils;

import android.content.Context;
import android.content.Intent;

import com.qingsongjia.qingsongjia.activity.RegisterActivity;
import com.qingsongjia.qingsongjia.activity.SetPasswordActivity;
import com.qingsongjia.qingsongjia.activity.VerifyPhoneActivity;
import com.qingsongjia.qingsongjia.driverschool.SchoolListActivity;
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
}

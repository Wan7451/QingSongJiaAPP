package com.qingsongjia.qingsongjia.utils;

import android.content.Context;
import android.content.Intent;

import com.qingsongjia.qingsongjia.driverschool.SchoolListActivity;

/**
 * Created by wanggang on 15/11/17.
 */
public class UIManager {

    public static void startSchoolList(Context context){
        Intent i=new Intent();
        i.setClass(context, SchoolListActivity.class);
        context.startActivity(i);
    }
}

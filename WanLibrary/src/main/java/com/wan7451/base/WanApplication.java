package com.wan7451.base;

import android.app.Activity;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wan7451.crashhandler.CrashHandler;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/19.
 */
public class WanApplication extends MultiDexApplication {

    private ArrayList<Activity> allAcitivties;

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
        //初始化图片加载
        Fresco.initialize(this);
        //处理异常
        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
        //所有的Activity
        allAcitivties = new ArrayList<>();
    }

    public ArrayList<Activity> getAllAcitivties() {
        return allAcitivties;
    }

    public void addAcitivty(Activity a) {
        if (!allAcitivties.contains(a))
            allAcitivties.add(a);
    }


    public void deleteActivity(Activity a) {
        if (allAcitivties.contains(a))
            allAcitivties.remove(a);
    }


    public void closeActivity(Class<? extends Activity> activity) {
        for (Activity a : allAcitivties) {
            if (a.getClass().getName().equals(activity.getName())) {
                a.finish();
            }
        }
    }

    public void exit() {
        for (Activity a : allAcitivties) {
            a.finish();
        }
    }

    public void showToast(String str) {
        if (!TextUtils.isEmpty(str))
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String str) {
        if (!TextUtils.isEmpty(str))
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

}

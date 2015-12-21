package com.qingsongjia.qingsongjia.others;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;


public class GetImageService extends Service {
    public static final String ACTION_SPLASH_IMG = "splash";
    public static final String ACTION_TOP_IMG = "image";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SPLASH_IMG.equals(action)) {
                handleSplashImg();
            } else if (ACTION_TOP_IMG.equals(action)) {
                handleTopImg();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public static void startDownSplashImage(Context context) {
        Intent i = new Intent();
        i.setClass(context, GetImageService.class);
        i.setAction(ACTION_SPLASH_IMG);
        context.startService(i);
    }

    public static void startDownTopImage(Context context) {
        Intent i = new Intent();
        i.setClass(context, GetImageService.class);
        i.setAction(ACTION_TOP_IMG);
        context.startService(i);
    }


    private void handleSplashImg() {
        NetRequest.getSplashImage(GetImageService.this, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                String data = response.getString(0);
                JSONObject object = JSONObject.parseObject(data);
                String path = object.getString("dri_file_path");

                LocalPreference.saveSplashImag(GetImageService.this, path);
                stopSelf();
            }

            @Override
            public void onResponseError(String error) {

            }
        });


    }


    private void handleTopImg() {

        NetRequest.getTopPictur(GetImageService.this, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                String data = response.getString(0);
                JSONObject object = JSONObject.parseObject(data);
                String path = object.getString("dri_file_path");
                LocalPreference.savaTopImgPath(GetImageService.this, path);
                stopSelf();
            }

            @Override
            public void onResponseError(String error) {

            }
        });


    }
}

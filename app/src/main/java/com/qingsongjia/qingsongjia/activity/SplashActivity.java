package com.qingsongjia.qingsongjia.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.others.GetImageService;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;

public class SplashActivity extends Activity {

    private SimpleDraweeView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_splash);

        img = (SimpleDraweeView) findViewById(R.id.splash_img);

        GetImageService.startDownSplashImage(this);
        GetImageService.startDownTopImage(this);

       String path= LocalPreference.getSplashImag(this);

        if(!TextUtils.isEmpty(path)){
            img.setImageURI(Uri.parse(path));
        }else {
            img.setImageURI(Uri.parse("http://7xlt5l.com1.z0.glb.clouddn.com/1450169401266eysxub1424213"));
        }




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goNext();
            }
        }, 2000);

    }

    private void goNext() {
//        User user = LocalPreference.getCurrentUser(this);
//        if (!TextUtils.isEmpty(user.getDri_type())) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
//        } else {
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        }
    }

}

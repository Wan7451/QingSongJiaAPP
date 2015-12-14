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
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SimpleDraweeView img = (SimpleDraweeView) findViewById(R.id.splash_img);
        img.setImageURI(Uri.parse("http://pic48.nipic.com/file/20140909/18903763_001139930001_2.jpg"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goNext();
            }
        }, 2000);

    }

    private void goNext() {
        User user = LocalPreference.getCurrentUser(this);
        if (!TextUtils.isEmpty(user.getDri_type())) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

}

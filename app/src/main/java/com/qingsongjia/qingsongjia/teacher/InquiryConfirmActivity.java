package com.qingsongjia.qingsongjia.teacher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

public class InquiryConfirmActivity extends WanActivity {


    @Override
    public void initView() {
        setContentTitle("预约");
        setBackFinish();
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_inquiry_confirm;
    }

}

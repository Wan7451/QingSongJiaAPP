package com.qingsongjia.qingsongjia.driverexam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

public class TeacherDetailActivity extends WanActivity {

    @Override
    public void initView() {
        setBackFinish();
        setContentTitle("详细信息");
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_teacher_detail;
    }

}

package com.qingsongjia.qingsongjia.activity;

import android.os.Bundle;
import android.view.View;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

/**
 * Created by Wan on 2015/12/5.
 */
public class SchoolInfoActivity extends WanActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_school_info);
    }

    @Override
    public void initView() {

    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }
}

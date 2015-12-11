package com.qingsongjia.qingsongjia.driverschool;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

public class SchoolDetailActivity extends WanActivity {


    private int id;

    @Override
    public void initView() {
        setContentTitle("驾校详情");
        setBackFinish();
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_school_info;
    }
}

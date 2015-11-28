package com.qingsongjia.qingsongjia.user;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

public class SettingActivity extends WanActivity {


    @Override
    public void initView() {
        setContentTitle("设置");
        setBackFinish();
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_setting;
    }

}

package com.qingsongjia.qingsongjia.driverschool;

import android.os.Bundle;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BusRouteActivity extends WanActivity {


    @Bind(R.id.luxian)
    TextView luxian;

    @Override
    public void initView() {
        setContentTitle("班车路线");
        setBackFinish();
        ButterKnife.bind(this);
        String lines = getIntent().getStringExtra("routeLine");
        luxian.setText(lines);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_bus_route;
    }

}

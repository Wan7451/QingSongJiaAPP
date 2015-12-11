package com.qingsongjia.qingsongjia.driverschool;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.qingsongjia.qingsongjia.adapter.CityDataAdapter;
import com.qingsongjia.qingsongjia.bean.CityData;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.wan7451.base.WanActivity;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AreaListActivity extends WanListActivity {


    ArrayList<CityData> data = new ArrayList<>();

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();
        setContentTitle("选择城市");
        setBackFinish();
    }

    @Override
    public WanAdapter getAdapter() {
        return new CityDataAdapter(getContext(), data, R.layout.item_text_only);
    }


    @Override
    protected void loadData() {
        data.clear();
        data.add(new CityData("", "", "全部", ""));
        ArrayList<CityData> d = getIntent().getParcelableArrayListExtra("data");
        data.addAll(d);
        loadFinish("");
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        App app = (App) getApplication();
        CityData city = getIntent().getParcelableExtra("city");

        if (posotion == 0) {
            app.setCurrCity(city);
            app.setCurrArea(null);
            colseActivity(CityListActivity.class);
            finish();
            return;
        }

        app.setCurrArea(data.get(posotion ));
        app.setCurrCity(city);

        colseActivity(CityListActivity.class);
        finish();
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }


}

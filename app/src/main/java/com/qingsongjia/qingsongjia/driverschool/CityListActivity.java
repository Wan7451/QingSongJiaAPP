package com.qingsongjia.qingsongjia.driverschool;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.qingsongjia.qingsongjia.adapter.CityDataAdapter;
import com.qingsongjia.qingsongjia.bean.CityData;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CityListActivity extends WanListActivity {

    ArrayList<CityData> data = new ArrayList<>();
    ArrayList<CityData> allCityData = new ArrayList<>();

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

        new Thread() {
            @Override
            public void run() {
                super.run();

                allCityData.clear();
                data.clear();

                InputStream inputStream = getResources().openRawResource(R.raw.table_export);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String item;
                try {
                    while (!TextUtils.isEmpty(item = reader.readLine())) {
                        String[] itemData = item.split(",");
                        allCityData.add(new CityData(itemData[0], itemData[1], itemData[2], itemData[3]));
                    }

                    reader.close();

                    data.add(new CityData("", "", "全部", ""));
                    for (int i = 0; i < allCityData.size(); i++) {
                        CityData d = allCityData.get(i);
                        if (d.getParentCodeName().equals("140000")) {
                            data.add(d);
                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadFinish("");
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

        if (posotion == 0) {
            App app = (App) getAppContext();
            app.setCurrCity(null);
            app.setCurrArea(null);
            finish();
            return;
        }

        CityData d = data.get(posotion);

        ArrayList<CityData> s = new ArrayList<>();

        for (int i = 0; i < allCityData.size(); i++) {
            CityData cd = allCityData.get(i);

            if (d.getCodeName().equals(cd.getParentCodeName())) {
                s.add(cd);
            }
        }

        UIManager.startAreaList(getContext(), s, d);
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }


}

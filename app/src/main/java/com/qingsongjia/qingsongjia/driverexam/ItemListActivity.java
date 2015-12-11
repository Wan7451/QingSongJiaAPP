package com.qingsongjia.qingsongjia.driverexam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ItemClickDataAdapter;
import com.qingsongjia.qingsongjia.bean.ItemClickData;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

public class ItemListActivity extends WanListActivity {

    public static final int TYPE_BMXZ = 0; //报名须知


    ArrayList<ItemClickData> datas = new ArrayList<>();

    int[] icons = {R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            };

    String[] bmxz = {"报名条件", "体检事项", "学车费用", "学车流程", "择校指南", "准驾车型", "作弊处罚"};
    String[] bmxzUrl = {"baomingtiaojian.html",
            "tijianshixiang.html",
            "xuechefeiyong.html",
            "xuecheliucheng.html",
            "zexiaozhinan.html",
            "zhunjiachexing.png",
            "zuobichufa.html"
    };
    private int type;

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case TYPE_BMXZ:
                setContentTitle("报名须知");
                datas.add(new ItemClickData(icons[0], bmxz[0], "", true));
                datas.add(new ItemClickData(icons[1], bmxz[1], "", true));
                datas.add(new ItemClickData(icons[2], bmxz[2], "", true));
                datas.add(new ItemClickData(icons[3], bmxz[3], "", true));
                datas.add(new ItemClickData(icons[4], bmxz[4], "", true));
                datas.add(new ItemClickData(icons[5], bmxz[5], "", true));
                datas.add(new ItemClickData(icons[6], bmxz[6], "", true));
                break;
        }


        //显示本地数据
        setIsShowLocalData();
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        ItemClickDataAdapter adapter = new ItemClickDataAdapter(getContext(), datas, R.layout.item_icontext_arrows);
        return adapter;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        switch (type) {
            case TYPE_BMXZ:
                String path = "file:///android_asset/html/kemu1/bmxz/";
                UIManager.startWebView(getContext(), bmxz[posotion], path + bmxzUrl[posotion]);
                break;
        }


    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}

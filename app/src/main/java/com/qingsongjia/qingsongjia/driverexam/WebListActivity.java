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

public class WebListActivity extends WanListActivity {

    private String rootPath = "file:///android_asset/html/kemu1/jtbz/jjss/cankao/";

    ArrayList<ItemClickData> datas = new ArrayList<>();//展示的数据

    String[] jtbzdq = {"变道", "减速慢行", "停车", "停止", "右转弯", "直行", "左转弯", "左转弯待转信号"};
    String[] jtbzdqPath = {"bd", "jsmx", "tc", "tz", "yzw", "zx", "zzw","zzwdz"};


    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("交警手势");
    }

    @Override
    public WanAdapter getAdapter() {
        return new ItemClickDataAdapter(getContext(), datas, R.layout.item_icontext_arrows);
    }

    @Override
    protected void loadData() {
        datas.add(new ItemClickData(R.drawable.icon_1, jtbzdq[0], "", true));
        datas.add(new ItemClickData(R.drawable.icon_2, jtbzdq[1], "", true));
        datas.add(new ItemClickData(R.drawable.icon_3, jtbzdq[2], "", true));
        datas.add(new ItemClickData(R.drawable.icon_4, jtbzdq[3], "", true));
        datas.add(new ItemClickData(R.drawable.icon_5, jtbzdq[4], "", true));
        datas.add(new ItemClickData(R.drawable.icon_6, jtbzdq[5], "", true));
        datas.add(new ItemClickData(R.drawable.icon_7, jtbzdq[6], "", true));
        loadFinish("");
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startWebView(getContext(),datas.get(posotion).getMainText(),rootPath+jtbzdqPath[posotion]+".html");
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}

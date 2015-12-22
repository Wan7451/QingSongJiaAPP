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

public class JiaoTongBiaoZhiListActivity extends WanListActivity {

    private String rootPath = "html/kemu1/jtbz/jtbzdq/";

    ArrayList<ItemClickData> datas=new ArrayList<>();//展示的数据

    String[]  jtbzdq={"禁令标志","警告标志","指示标志","指路标志","道路施工安全标志","旅游区标志","辅助标志"};
    String[]  jtbzdqPath={"jlbz","jgbz","zsbz","zlbz","dlsgaqbz","lyqbz","fzbz"};
    String[]  detail={"biaozhi_grid_2_jinling_sign.json",


    "biaozhi_grid_2_fuzhu_sign.json"};

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("交通标志大全");
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
        UIManager.startJiaoTongBiaoZhiGrid(getContext(),datas.get(posotion).getMainText(),rootPath+jtbzdqPath[posotion]);
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}

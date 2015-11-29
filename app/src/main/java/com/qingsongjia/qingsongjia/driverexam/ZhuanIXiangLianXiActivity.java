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

public class ZhuanIXiangLianXiActivity extends WanListActivity {

    ArrayList<ItemClickData> datas = new ArrayList<>();

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
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("专项练习");
        setIsShowLocalData();
    }

    @Override
    protected void loadData() {

        datas.add(new ItemClickData(R.drawable.icon_yuan_hong, "专项练习", "48", true));
        datas.add(new ItemClickData(R.drawable.icon_yuan_lv, "专项练习", "48", true));
        datas.add(new ItemClickData(R.drawable.icon_yuan_zi, "专项练习", "48", true));
        datas.add(new ItemClickData(R.drawable.icon_yuan_lan, "专项练习", "48", true));
        datas.add(new ItemClickData(R.drawable.icon_yuan_lanlv, "专项练习", "48", true));
        datas.add(new ItemClickData(R.drawable.icon_yuan_cheng, "专项练习", "48", true));
        datas.add(new ItemClickData(R.drawable.icon_yuan_huang, "专项练习", "48", true));
        datas.add(new ItemClickData(R.drawable.icon_yuan_hong, "专项练习", "48", true));
        datas.add(new ItemClickData(R.drawable.icon_yuan_fei, "专项练习", "48", true));

        loadFinish("");
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startExamTest(getContext(),ExamTestActivity.TYPE_NORMAL);
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}

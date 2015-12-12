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

public class XinShouShangLuActivity extends WanListActivity {

    private String rootPath = "file:///android_asset/html/kemu1/xssl/";

    ArrayList<ItemClickData> datas = new ArrayList<>();//展示的数据

    String[] data = {"驾车技巧",
            "驾照挂失",
            "驾照换证",
            "驾照遗失",
            "年检年审",
            "停车技巧"};
    String[] dataPath = {"jcjq", "jzgs", "jzhz",
            "jzys", "njns", "tcjq"};


    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("新手上路");
    }

    @Override
    public WanAdapter getAdapter() {
        return new ItemClickDataAdapter(getContext(), datas, R.layout.item_icontext_arrows);
    }

    @Override
    protected void loadData() {
        datas.clear();
        datas.add(new ItemClickData(R.drawable.icon_1, data[0], "", true));
        datas.add(new ItemClickData(R.drawable.icon_2, data[1], "", true));
        datas.add(new ItemClickData(R.drawable.icon_3, data[2], "", true));
        datas.add(new ItemClickData(R.drawable.icon_4, data[3], "", true));
        datas.add(new ItemClickData(R.drawable.icon_5, data[4], "", true));
        datas.add(new ItemClickData(R.drawable.icon_6, data[5], "", true));
        loadFinish("");
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

        switch (posotion){
            case 0:
                UIManager.startXinShouShangLuList(getContext(),1);
                break;
            case 5:
                UIManager.startXinShouShangLuList(getContext(),2);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                UIManager.startWebView(getContext(),data[posotion],rootPath+dataPath[posotion]+".html");
                break;
        }
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}

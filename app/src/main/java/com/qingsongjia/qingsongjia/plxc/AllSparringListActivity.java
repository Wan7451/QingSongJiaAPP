package com.qingsongjia.qingsongjia.plxc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.PenLianAdapter;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

public class AllSparringListActivity extends WanListActivity {

    private ArrayList<PeiLian> data = new ArrayList<>();

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("陪练列表");
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public WanAdapter getAdapter() {
        PenLianAdapter adapter = new PenLianAdapter(getContext(), data, R.layout.item_order_list);
        return adapter;
    }

    @Override
    protected void loadData() {
        NetRequest.getPeiLianList(getContext(),null, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), PeiLian.class));
                }
                loadFinish("暂时没有陪练信息");
            }

            @Override
            public void onResponseError(String error) {

            }
        });
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        PeiLian item = data.get(posotion);

        switch (item.getStatus()) {
            case "1":
                //   state= "未抢单";
                UIManager.startPenLianDetail(getContext(), item);

                break;
            case "2":
                //   state= "已抢单";
                break;
            case "3":
                //   state="已确认";
                break;
            case "4":
                //   state="已取消";
                break;
        }
//        UIManager.start
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}

package com.qingsongjia.qingsongjia.plxc;

import android.content.Context;
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
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 学员    陪练列表
 */
public class S_SparringListActivity extends WanListActivity {


    private ArrayList<PeiLian> data = new ArrayList<>();

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("陪练行程");
//        setRightText("编辑");
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        PenLianAdapter adapter = new PenLianAdapter(getContext(), data, R.layout.item_order_list);
//        View header = getLayoutInflater().inflate(R.layout.activity_my_test, null);
//        adapter.addHeaderView(header);
        return adapter;
    }

    @Override
    protected void loadData() {

        NetRequest.loadMyPenLian(getContext(),null, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), PeiLian.class));
                }
                loadFinish("");
            }

            @Override
            public void onResponseError(String error) {

            }
        });
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startPenLianDetail(getContext(),data.get(posotion));
    }

}

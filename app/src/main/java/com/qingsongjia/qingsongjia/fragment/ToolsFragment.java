package com.qingsongjia.qingsongjia.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.PenLianAdapter;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListFragment;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 学员端  抢单
 */

public class ToolsFragment extends WanListFragment {
    private ArrayList<PeiLian> data = new ArrayList<>();

    @Override
    protected boolean isShowTitleView() {
        return false;
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    protected boolean loadData() {

        NetRequest.getPeiLianList(getContext(),null, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
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


        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        PenLianAdapter adapter = new PenLianAdapter(getContext(), data, R.layout.item_order_list);
        return adapter;
    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        PeiLian item = data.get(posotion);

        switch (item.getStatus()) {
            case "1":
                //   state= "未抢单";
                UIManager.startPenLianDetail(getContext(),item);

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

}

package com.qingsongjia.qingsongjia.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanListFragment;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ToolsFragment extends WanListFragment {
    private ArrayList<PeiLian> data=new ArrayList<>();

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

        NetRequest.getPeiLianList(getContext(), new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if(response.size()>0){
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
        MyTestAdapter adapter=new MyTestAdapter(getContext(),data,R.layout.item_mytest_list);
        return adapter;
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }

    static class MyTestAdapter extends WanAdapter<PeiLian>{

        public MyTestAdapter(Context context, List<PeiLian> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, PeiLian item) {

        }
    }
}

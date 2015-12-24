package com.qingsongjia.qingsongjia.exchange;

import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExchangeListAdapter;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListFragment;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

public class ExchangeItemFragment extends WanListFragment {

    private String typel;

    ArrayList<String> date=new ArrayList<>();
    private String type;

    public ExchangeItemFragment() {
    }




    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    protected boolean loadData() {

        NetRequest.loadExchange(getContext(),type, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {

            }

            @Override
            public void onResponseError(String error) {

            }
        });

        date.add("");
        date.add("");
        date.add("");
        date.add("");
        date.add("");
        date.add("");

        loadFinish("");

        return false;
    }

    @Override
    public WanAdapter getAdapter() {

        return new ExchangeListAdapter(getContext(),date,R.layout.item_exchange_list);
    }

    @Override
    public void initView(View v) {
        super.initView(v);
        getMainView().getRefreshableView().removeItemDecoration(getItemDecore());
        type = getArguments().getString("type");

    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startExchangeDeatil(getContext(),posotion);
    }
}

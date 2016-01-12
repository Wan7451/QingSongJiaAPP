package com.qingsongjia.qingsongjia.exchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.MainActivity;
import com.qingsongjia.qingsongjia.adapter.ExchangeListAdapter;
import com.qingsongjia.qingsongjia.bean.Exchange;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListFragment;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class ExchangeItemFragment extends WanListFragment {


    ArrayList<Exchange> data = new ArrayList<>();
    private String type;

    public ExchangeItemFragment() {
    }


    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    protected boolean loadData() {

        NetRequest.loadExchange(getContext(), null, type, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), Exchange.class));
                }
                loadFinish("暂时内容，赶快发帖吧~");

            }

            @Override
            public void onResponseError(String error) {
                loadError();
            }
        });


        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        return new ExchangeListAdapter(getContext(), data, R.layout.item_exchange_list);
    }

    @Override
    public void initView(View v) {
        super.initView(v);
        getMainView().getRefreshableView().removeItemDecoration(getItemDecore());
        type = getArguments().getString("type");

        ((MainActivity) getActivity()).setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getContext(), PushExchangeActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startExchangeDeatil(getContext(), data.get(posotion).getDid());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(EventData data) {
        if (data.getType() == EventData.TYPE_REFRESH_EXCHANGE) {
            refreshing();
        }
    }
}

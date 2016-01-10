package com.qingsongjia.qingsongjia.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.JiFei;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyJiFeiActivity extends WanActivity {


    @Bind(R.id.jifei_count)
    TextView jifeiCount;
    @Bind(R.id.jifei_shop)
    LinearLayout jifeiShop;
    @Bind(R.id.jifei_record)
    LinearLayout jifeiRecord;
    @Bind(R.id.jifei_checkout)
    RecyclerView jifeiCheckout;


    private ArrayList<JiFei> data = new ArrayList<>();
    private JiFeiAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setContentTitle("我的积分");
        setBackFinish();


        int jifen = getIntent().getIntExtra("jifen", 0);
        jifeiCount.setText(jifen + "");

        adapter = new JiFeiAdapter(getContext(), data, R.layout.item_jifei_checkout);
        jifeiCheckout.setAdapter(adapter);
        jifeiCheckout.setLayoutManager(new LinearLayoutManager(getContext()));
        jifeiCheckout.addItemDecoration(new WanItemDecoration(getContext(), WanItemDecoration.VERTICAL_LIST));


        loadData();


    }

    private void loadData() {

        NetRequest.getMyJiFen(getContext(),null, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), JiFei.class));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onResponseError(String error) {

            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_ji_fei;
    }

    static class JiFeiAdapter extends WanAdapter<JiFei> {

        public JiFeiAdapter(Context context, List<JiFei> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, JiFei item) {
                holder.setText(R.id.checkout_subject,item.getType());
                holder.setText(R.id.checkout_charge,item.getWorth()+"");
                holder.setText(R.id.checkout_time,item.getCreate_tm_str());
                holder.setText(R.id.checkout_from,"");
        }
    }
}

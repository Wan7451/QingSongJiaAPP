package com.qingsongjia.qingsongjia.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
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


    private ArrayList<String> data = new ArrayList<>();
    private JiFeiAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setContentTitle("我的积分");
        setBackFinish();

        adapter = new JiFeiAdapter(getContext(), data, R.layout.item_jifei_checkout);
        jifeiCheckout.setAdapter(adapter);
        jifeiCheckout.setLayoutManager(new LinearLayoutManager(getContext()));
        jifeiCheckout.addItemDecoration(new WanItemDecoration(getContext(), WanItemDecoration.VERTICAL_LIST));

        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        adapter.notifyDataSetChanged();

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_ji_fei;
    }

    static class JiFeiAdapter extends WanAdapter<String> {

        public JiFeiAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, String item) {

        }
    }
}

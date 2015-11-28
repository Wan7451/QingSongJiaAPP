package com.qingsongjia.qingsongjia.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyYuEActivity extends WanActivity {

    @Bind(R.id.yue_count)
    TextView yueCount;
    @Bind(R.id.yue_tixian)
    LinearLayout yueTixian;
    @Bind(R.id.yue_checkout)
    RecyclerView yueCheckout;
    private JiFeiAdapter adapter;
    private ArrayList<String> data=new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setContentTitle("我的余额");
        setBackFinish();

        adapter = new JiFeiAdapter(getContext(), data, R.layout.item_jifei_checkout);
        yueCheckout.setAdapter(adapter);
        yueCheckout.setLayoutManager(new LinearLayoutManager(getContext()));
        yueCheckout.addItemDecoration(new WanItemDecoration(getContext(), WanItemDecoration.VERTICAL_LIST));

        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        adapter.notifyDataSetChanged();

        yueTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startTiXian(getContext());
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_yu_e;
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

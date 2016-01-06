package com.qingsongjia.qingsongjia.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.JiFei;
import com.qingsongjia.qingsongjia.bean.YuE;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的余额
 */
public class MyYuEActivity extends WanActivity {

    @Bind(R.id.yue_count)
    TextView yueCount;
    @Bind(R.id.yue_tixian)
    LinearLayout yueTixian;
    @Bind(R.id.yue_checkout)
    RecyclerView yueCheckout;
    private JiFeiAdapter adapter;
    private ArrayList<YuE> data=new ArrayList<>();
    private int money;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setContentTitle("我的余额");
        setBackFinish();

        money = getIntent().getIntExtra("money",0);
        yueCount.setText(money +"");

        adapter = new JiFeiAdapter(getContext(), data, R.layout.item_jifei_checkout);
        yueCheckout.setAdapter(adapter);
        yueCheckout.setLayoutManager(new LinearLayoutManager(getContext()));
        yueCheckout.addItemDecoration(new WanItemDecoration(getContext(), WanItemDecoration.VERTICAL_LIST));

        loadData();


        yueTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startTiXian(getContext(), money);
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_yu_e;
    }

    static class JiFeiAdapter extends WanAdapter<YuE> {

        public JiFeiAdapter(Context context, List<YuE> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, YuE item) {
            holder.setText(R.id.checkout_subject,item.getType());
            holder.setText(R.id.checkout_charge,item.getWorth()+"");
            holder.setText(R.id.checkout_time,item.getCreate_tm_str());
            holder.setText(R.id.checkout_from,item.getOpr()+" "+item.getCard_num());
        }
    }


    private void loadData() {

        NetRequest.getMyYuE(getContext(), new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), YuE.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onResponseError(String error) {

            }
        });

    }

}

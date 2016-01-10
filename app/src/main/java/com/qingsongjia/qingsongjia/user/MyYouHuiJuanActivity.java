package com.qingsongjia.qingsongjia.user;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.TarenaTime;
import com.qingsongjia.qingsongjia.bean.YouHuiJuan;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyYouHuiJuanActivity extends WanListActivity {

    private ArrayList<YouHuiJuan> data = new ArrayList<>();


    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("优惠券");
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        return new YouHuiJuanAdapter(getContext(), data, R.layout.item_user_yhj);
    }

    @Override
    protected void loadData() {

        NetRequest.loadMyYouHuiJuan(getContext(),null, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONObject.parseArray(response.toJSONString(), YouHuiJuan.class));
                }
                loadFinish("暂时没有优惠劵");
            }

            @Override
            public void onResponseError(String error) {
                loadError();
            }
        });

    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    static class YouHuiJuanAdapter extends WanAdapter<YouHuiJuan> {
        SimpleDateFormat format;

        public YouHuiJuanAdapter(Context context, List<YouHuiJuan> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
            format = new SimpleDateFormat("yyyy年MM月dd日 HH时");
        }

        @Override
        public void convert(WanViewHolder holder, int position, YouHuiJuan item) {
            holder.setText(R.id.yhj_charge, item.getDri_money_amount() + "");
            long time = item.getDri_use_end_dt().getTime();
            holder.setText(R.id.yhj_endTime, format.format(new Date(time)));
            holder.setText(R.id.yhj_tj, "满" + item.getDri_satisfy_amount() + "使用");

        }
    }
}

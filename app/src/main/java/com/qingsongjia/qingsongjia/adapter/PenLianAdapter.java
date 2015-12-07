package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.List;

/**
 * 陪练适配器
 * Created by wanggang on 15/12/7.
 */
public class PenLianAdapter extends WanAdapter<PeiLian> {

    public PenLianAdapter(Context context, List<PeiLian> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(WanViewHolder holder, int position, PeiLian item) {
        TextView time = holder.getView(R.id.time);
        time.setText(item.getMeetingDate_str() + " " + item.getMeetingTime() + "时");
        TextView price = holder.getView(R.id.price);
        price.setText(item.getDri_price() + "元");
        TextView status = holder.getView(R.id.pl_tatus);

        String state = "";
        switch (item.getStatus()) {
            case "1":
                state = "未抢单";
                break;
            case "2":
                state = "已抢单";
                break;
            case "3":
                state = "已确认";
                break;
            case "4":
                state = "已取消";
                break;
        }

        status.setText(state);
    }
}
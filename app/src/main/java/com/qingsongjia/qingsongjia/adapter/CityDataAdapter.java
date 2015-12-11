package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.CityData;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.List;

/**
 * Created by wanggang on 15/12/11.
 */
public class CityDataAdapter extends WanAdapter<CityData> {

    public CityDataAdapter(Context context, List<CityData> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(WanViewHolder holder, int position, CityData item) {
        holder.setText(R.id.text, item.getName());
    }
}



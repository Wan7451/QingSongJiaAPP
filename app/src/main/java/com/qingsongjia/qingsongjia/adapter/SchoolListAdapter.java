package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.School;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.List;

/**
 * Created by wanggang on 15/11/16.
 */
public class SchoolListAdapter extends WanAdapter<School> {

    public SchoolListAdapter(Context context, List<School> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(WanViewHolder holder, int position, School item) {
    }
}

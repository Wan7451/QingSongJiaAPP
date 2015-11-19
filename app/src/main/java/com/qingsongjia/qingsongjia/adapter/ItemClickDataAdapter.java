package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.view.View;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.ItemClickData;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.List;

/**
 * 左边图片，中间文字，右边文字、箭头 适配器
 * RecycleView
 * 对应布局 R.layout.item_icontext_arrows
 * Created by wanggang on 15/11/19.
 */
public class ItemClickDataAdapter extends WanAdapter<ItemClickData> {

    public ItemClickDataAdapter(Context context, List<ItemClickData> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(WanViewHolder holder, int position, ItemClickData item) {
        holder.setText(R.id.icontext_mainText, item.getMainText());
        holder.setText(R.id.icontext_secondText, item.getSecondText());
        if (item.getIcon() != 0) {
            holder.setImageResource(R.id.icontext_icon, item.getIcon());
        }
        if (item.isShowArrow()) {
            holder.getView(R.id.icontext_arrow).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.icontext_arrow).setVisibility(View.GONE);
        }

    }
}

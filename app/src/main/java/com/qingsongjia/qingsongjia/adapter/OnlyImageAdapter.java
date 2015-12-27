package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.List;

/**
 * Created by wanggang on 15/12/26.
 */
public class OnlyImageAdapter extends WanAdapter<String> {

    private final DisplayImageOptions displayOptions;

    /**
     * 构造器
     *
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public OnlyImageAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        displayOptions = ((App) context.getApplicationContext()).getDisplayOptions();
    }

    @Override
    public void convert(WanViewHolder holder, int position, String item) {
       ImageView exchImg= holder.getView(R.id.exchange_img);
        ImageLoader.getInstance().displayImage(item,exchImg,displayOptions);
    }
}

package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.wan7451.wanadapter.list.CommonAdapter;
import com.wan7451.wanadapter.list.ViewHolder;

import java.util.List;

/**
 * 上图下字的适配器
 */
public class ExamImageTextAdapter extends CommonAdapter<ExamImageText>{

    public ExamImageTextAdapter(Context context, List<ExamImageText> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper,int position, ExamImageText item) {
        helper.setText(R.id.imageText_text,item.getShowText());
        helper.setImageResource(R.id.imageText_image,item.getShowImageRes());
    }
}

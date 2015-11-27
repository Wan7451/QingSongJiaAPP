package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.wan7451.wanadapter.list.CommonAdapter;
import com.wan7451.wanadapter.list.ViewHolder;

import java.util.List;

/**
 * 上图下字的适配器
 */
public class ExamImageAdapter extends CommonAdapter<ExamImageText>{


    private int choicePosition;

    public void setChoicePosition(int choicePosition) {
        this.choicePosition = choicePosition;
    }

    public ExamImageAdapter(Context context, List<ExamImageText> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper,int position, ExamImageText item) {
        helper.setImageResource(R.id.imageText_image,item.getShowImageRes());
        ImageView imageView= helper.getView(R.id.imageText_choice);
        if(choicePosition==position){
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.drawable.icon_menu_choice);
        }else {
            imageView.setVisibility(View.GONE);
        }
    }
}

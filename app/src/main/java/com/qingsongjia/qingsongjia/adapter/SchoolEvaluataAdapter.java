package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.SchoolEvaluate;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.List;

/**
 * 驾校评论列表
 */
public class SchoolEvaluataAdapter extends WanAdapter<SchoolEvaluate> {
    /**
     * 构造器
     *
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public SchoolEvaluataAdapter(Context context, List<SchoolEvaluate> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(WanViewHolder holder, int position, SchoolEvaluate item) {
        holder.setText(R.id.evaluate_name, item.getCreate_nm());
        holder.setText(R.id.evaluate_time, item.getUpdate_tm_str());
        holder.setText(R.id.evaluate_count, item.getDri_praise_num() + "");
        holder.setText(R.id.evaluate_text, item.getDri_remark());

        SimpleDraweeView icon = holder.getView(R.id.evaluate_icon);
        if (TextUtils.isEmpty(item.getDri_file_path())) {
            icon.setImageURI(Uri.parse("res:// /"+R.drawable.default_head));
        }else {
            icon.setImageURI(Uri.parse(item.getDri_file_path()));
        }
    }
}

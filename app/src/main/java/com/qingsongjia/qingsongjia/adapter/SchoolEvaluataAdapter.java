package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.SchoolEvaluate;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
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
    public void convert(final WanViewHolder holder, int position, final SchoolEvaluate item) {
        holder.setText(R.id.evaluate_name, item.getCreate_nm());
        holder.setText(R.id.evaluate_time, item.getUpdate_tm_str());
        holder.setText(R.id.evaluate_count, item.getDri_like_count() + "");
        holder.setText(R.id.evaluate_text, item.getDri_remark());


        ImageView isLike = holder.getView(R.id.evaluate_isLike);
        if (item.getDri_is_like() > 0) {
            isLike.setImageResource(R.drawable.icon_exch_zaned);
        } else {
            isLike.setImageResource(R.drawable.icon_exch_zan);
        }

        isLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getDri_is_like() > 0) {
                    NetRequest.cancelLikeComment(getContext(), view, item.getDid(), new NetUtils.NetUtilsHandler() {
                        @Override
                        public void onResponseOK(JSONArray response, int total) {
                            item.setDri_is_like(0);
                            item.setDri_like_count(item.getDri_like_count() - 1);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onResponseError(String error) {

                        }
                    });
                } else {
                    NetRequest.likeComment(getContext(), view, item.getDid(), new NetUtils.NetUtilsHandler() {
                        @Override
                        public void onResponseOK(JSONArray response, int total) {
                            item.setDri_is_like(1);
                            item.setDri_like_count(item.getDri_like_count() + 1);
                            notifyDataSetChanged();

                        }

                        @Override
                        public void onResponseError(String error) {

                        }
                    });
                }
            }
        });

        SimpleDraweeView icon = holder.getView(R.id.evaluate_icon);
        if (TextUtils.isEmpty(item.getDri_file_path())) {
            icon.setImageURI(Uri.parse("res:// /" + R.drawable.default_head));
        } else {
            icon.setImageURI(Uri.parse(item.getDri_file_path()));
        }
    }
}

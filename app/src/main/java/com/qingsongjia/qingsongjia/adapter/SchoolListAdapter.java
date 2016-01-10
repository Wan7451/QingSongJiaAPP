package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.School;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
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
    public void convert(final WanViewHolder holder, int position, final School item) {
        final SimpleDraweeView icon = holder.getView(R.id.schoollist_iv_logo);
        if (TextUtils.isEmpty(item.getDri_file_path())) {
            icon.setImageURI(Uri.parse("res:// /" + R.drawable.default_head));
        } else {
            icon.setImageURI(Uri.parse(item.getDri_file_path()));
        }

        holder.setText(R.id.schoollist_tv_name, item.getDri_nm());
        holder.setText(R.id.schoollist_tv_price, item.getDri_money() + "元起");
        holder.setText(R.id.schoollist_tv_attent, item.getDri_care() + "人关注");
        holder.setText(R.id.schoollist_tv_zan, item.getDri_praise() + "人赞");


        final ImageView support = holder.getView(R.id.schoollist_iv_support);
        if (item.getPraise_if() > 0) {
            support.setImageResource(R.drawable.icon_exch_zaned);
        } else {
            support.setImageResource(R.drawable.icon_exch_zan);
        }

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (item.getPraise_if() == 0) {
                    //判断是否登录
                    if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
                        UIManager.startLogin(getContext());
                        return;
                    }
                    //点攒
                    NetRequest.schoolZan(getContext(),view, item.getDri_campus_id(), new NetUtils.NetUtilsHandler() {
                        @Override
                        public void onResponseOK(JSONArray response, int total) {
                            item.setDri_praise(item.getDri_praise()+1);
                            item.setPraise_if(1);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onResponseError(String error) {

                        }
                    });
                } else {
                    //判断是否登录
                    if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
                        UIManager.startLogin(getContext());
                        return;
                    }

                    //取消点赞
                    NetRequest.schoolCancelZan(getContext(),view, item.getDri_campus_id(), new NetUtils.NetUtilsHandler() {
                        @Override
                        public void onResponseOK(JSONArray response, int total) {
                            item.setDri_praise(item.getDri_praise()-1);
                            item.setPraise_if(0);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onResponseError(String error) {

                        }
                    });
                }
            }
        });

    }
}

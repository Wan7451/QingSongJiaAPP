package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
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
       SimpleDraweeView icon= holder.getView(R.id.schoollist_iv_logo);
        if(TextUtils.isEmpty(item.getDri_file_path())){
            icon.setImageURI(Uri.parse("res:// /"+R.drawable.default_head));
        }else {
            icon.setImageURI(Uri.parse(item.getDri_file_path()));
        }

        holder.setText(R.id.schoollist_tv_name,item.getDri_nm());
        holder.setText(R.id.schoollist_tv_price,item.getDri_money()+"元起");
        holder.setText(R.id.schoollist_tv_attent,item.getDri_care()+"人关注");

      ImageView support= holder.getView(R.id.schoollist_iv_support);
        if(item.getDri_praise()>0){
            support.setImageResource(R.drawable.icon_exch_zaned);
        }else {
            support.setImageResource(R.drawable.icon_exch_zan);
        }

    }
}

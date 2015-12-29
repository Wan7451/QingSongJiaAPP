package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.Exchange;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.wanadapter.list.CommonAdapter;
import com.wan7451.wanadapter.list.ViewHolder;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 交流中心适配器
 * Created by wanggang on 15/12/8.
 */
public class ExchangeListAdapter extends WanAdapter<Exchange> {

    public ExchangeListAdapter(Context context, List<Exchange> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(WanViewHolder holder, int position, final Exchange item) {


//        Drawable drawable = getContext().getResources().getDrawable(R.drawable.icon_exch_jing);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//                drawable.getIntrinsicHeight());
//
//        SpannableString spannable = new SpannableString("[smile]"+content.getText()
//                .toString() );
//
//        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
//        spannable.setSpan(span, 0,
//                "[smile]".length(),
//                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView content = holder.getView(R.id.exch_content);
        TextView name = holder.getView(R.id.exch_name);
        TextView time = holder.getView(R.id.exch_time);
        final TextView zan = holder.getView(R.id.exch_zan);
        TextView comment = holder.getView(R.id.exch_comment);
        final SimpleDraweeView icon = holder.getView(R.id.exch_icon);

        content.setText(item.getDri_text());
        name.setText(item.getCreate_nm());
        time.setText(item.getCreate_tm_str());
        comment.setText("评论 " + item.getComment_count());
        zan.setText("赞 " + item.getPraise());

        if (item.getPraise_if() == 0) {
            Drawable z = getContext().getResources().getDrawable(R.drawable.icon_exch_zan);
            z.setBounds(0, 0, z.getMinimumWidth(),
                    z.getMinimumHeight());
            zan.setCompoundDrawables(z, null, null, null);
            zan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //判断是否登录
                    if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
                        UIManager.startLogin(getContext());
                        return;
                    }


                    NetRequest.zanExchange(getContext(), item.getId(), new NetUtils.NetUtilsHandler() {
                        @Override
                        public void onResponseOK(JSONArray response, int total) {
                            item.setPraise_if(1);
                            item.setPraise(item.getPraise()+1);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onResponseError(String error) {

                        }
                    });
                }
            });
        }else {
            Drawable zed = getContext().getResources().getDrawable(R.drawable.icon_exch_zaned);
            zed.setBounds(0, 0, zed.getMinimumWidth(),
                    zed.getMinimumHeight());
            zan.setCompoundDrawables(zed, null, null, null);
            zan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //判断是否登录
                    if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
                        UIManager.startLogin(getContext());
                        return;
                    }

                    NetRequest.offZanExchange(getContext(), item.getId(), new NetUtils.NetUtilsHandler() {
                        @Override
                        public void onResponseOK(JSONArray response, int total) {
                            item.setPraise_if(0);
                            item.setPraise(item.getPraise()-1);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onResponseError(String error) {

                        }
                    });
                }
            });
        }



        if (!TextUtils.isEmpty(item.getUser_file())) {
            icon.setImageURI(Uri.parse(item.getUser_file()));
        }

        if (!TextUtils.isEmpty(item.getDri_image_url())) {
            String urls = item.getDri_image_url();
            ArrayList<String> drawablws = new ArrayList<>();

            if (urls.contains(",")) {
                String[] items = urls.split(",");
                for (int i = 0; i < items.length; i++) {
                    drawablws.add(items[i]);
                }
            } else {
                drawablws.add(urls);
            }
            GridView imgs = holder.getView(R.id.exch_imgs);
            ImgAdapter adapter = new ImgAdapter(getContext(), drawablws, R.layout.item_exch_icon);
            imgs.setAdapter(adapter);
        }


    }


    static class ImgAdapter extends CommonAdapter<String> {

        public ImgAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, int position, String item) {
            SimpleDraweeView icon = (SimpleDraweeView) helper.getConvertView();
            icon.setImageURI(Uri.parse(item));
        }
    }
}

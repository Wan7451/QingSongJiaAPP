package com.qingsongjia.qingsongjia.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
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
public class ExchangeListAdapter extends WanAdapter<String> {

    public ExchangeListAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(WanViewHolder holder, int position, String item) {

       TextView content= holder.getView(R.id.exch_content);

        Drawable drawable = getContext().getResources().getDrawable(R.drawable.icon_exch_jing);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());

        SpannableString spannable = new SpannableString("[smile]"+content.getText()
                .toString() );

        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannable.setSpan(span, 0,
                "[smile]".length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        content.setText(spannable);

        GridView imgs = holder.getView(R.id.exch_imgs);
        ArrayList<String> drawablws = new ArrayList<>();
        drawablws.add("");
        drawablws.add("");
        drawablws.add("");
        drawablws.add("");
        ImgAdapter adapter = new ImgAdapter(getContext(), drawablws, R.layout.item_exch_icon);
        imgs.setAdapter(adapter);
    }


    static class ImgAdapter extends CommonAdapter<String> {

        public ImgAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, int position, String item) {
            SimpleDraweeView icon = (SimpleDraweeView) helper.getConvertView();
            icon.setImageURI(Uri.parse("http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a446237cd252b39c22720e0df3d7c3.jpg"));
        }
    }
}

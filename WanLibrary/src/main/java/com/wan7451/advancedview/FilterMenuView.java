package com.wan7451.advancedview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wan7451.choicepicture.ChoicePicturesAdapter;
import com.wan7451.wanadapter.mylibrary.R;
import com.wan7451.wanadapter.recycle.DensityUtil;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;


/**
 * Created by wanggang on 15/11/16.
 */
public class FilterMenuView {

    public static PopupWindow window;

    //显示过滤菜单
    public static void showFilterMenu(Activity context, ArrayList<String> data, View gen, final WanAdapter.OnItemClickListener l) {

        TypedArray actionbarSizeTypedArray = context.obtainStyledAttributes(new int[] {
                android.R.attr.actionBarSize
        });
        final int h = (int) (actionbarSizeTypedArray.getDimension(0, 0)+0.5);

        gen.measure(0, 0);
        int height = gen.getMeasuredHeight();

        if (window != null) {
            if (window.isShowing()) {
                window.dismiss();
            } else {
                Rect frame = new Rect();
                context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;
                window.showAtLocation(gen, Gravity.TOP, 0,height+ h + statusBarHeight);
            }
            return;
        }
        window = new PopupWindow(context);

        DisplayMetrics outMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);


        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        window.setWidth(outMetrics.widthPixels);
        window.setHeight(outMetrics.heightPixels -height -h - statusBarHeight);

        View v = LayoutInflater.from(context).inflate(R.layout.layout_list_filter, null);

        final RecyclerView view = (RecyclerView) v.findViewById(R.id.filterView_list);//过滤器的第二个界面


        TextAdapter adapter = new TextAdapter(context, data, android.R.layout.simple_list_item_1);
        WanItemDecoration decoration = new WanItemDecoration(context, WanItemDecoration.VERTICAL_LIST);
        view.addItemDecoration(decoration);
        view.setAdapter(adapter);
        adapter.setOnItemClickListener(new WanAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int posotion, WanViewHolder holder) {
                window.dismiss();
                l.onItemClickListener(posotion,holder);
            }
        });
        view.setLayoutManager(new LinearLayoutManager(context));

        v.findViewById(R.id.filterView_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击空白
                window.dismiss();
            }
        });
        window.setContentView(v);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(null);
        window.showAtLocation(gen, Gravity.TOP, 0,height+ h + statusBarHeight);
    }

    static class TextAdapter extends WanAdapter<String> {


        protected TextAdapter(Context context, ArrayList<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, String item) {
            TextView text = holder.getView(android.R.id.text1);
            text.setTextColor(getContext().getResources().getColor(R.color.text_color));
            text.setText(item);
            text.setTextSize(DensityUtil.px2dip(getContext(), 40));
        }

        @Override
        public void customItemViewStyle(View itemView) {
//            itemView.setBackgroundResource(R.drawable.item_bg);
        }
    }
}

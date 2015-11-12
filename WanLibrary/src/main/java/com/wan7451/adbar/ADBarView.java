package com.wan7451.adbar;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wan7451.wanadapter.mylibrary.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 广告条
 * Created by Wan7451 on 2015/7/12.
 */
public class ADBarView extends LinearLayout implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    private AutoScrollViewPager mADBarView;
    private RadioGroup mADBarIndicator;
    private View mainView;
    private LayoutInflater layoutInflater;
    private ImagePagerAdapter adapter;

    public ADBarView(Context context) {
        super(context);
        init();
    }

    public ADBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        layoutInflater = LayoutInflater.from(getContext());
        mainView = layoutInflater.inflate(R.layout.layout_adbar, null);
        mADBarView = (AutoScrollViewPager) mainView.findViewById(R.id.adbarview_adbar);
        mADBarIndicator = (RadioGroup) mainView.findViewById(R.id.adbarview_indicate);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainView.setLayoutParams(lp);
        mADBarView.setOnTouchListener(this);
        mADBarView.addOnPageChangeListener(this);


    }

    /**
     * 设置广告条内展示的内容
     *
     * @param urls 展示的内容
     */
    public void setShowURLs(ArrayList<String> urls) {
        if (urls == null)
            return;

        for (int i = 0; i < urls.size(); i++) {
            RadioButton indicator = (RadioButton) layoutInflater.inflate(R.layout.item_adbar_indicator, null);
            mADBarIndicator.addView(indicator);
        }

        adapter = new ImagePagerAdapter(getContext(), urls);
        mADBarView.setAdapter(adapter.setInfiniteLoop(true));
        mADBarView.setInterval(5000);

        if (urls.size() > 1) {
            mADBarView.startAutoScroll();
        } else {
            mADBarIndicator.setVisibility(INVISIBLE);
        }

        mADBarView.setCurrentItem(10000);


        addView(mainView);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton indicator = (RadioButton) mADBarIndicator.getChildAt(position % mADBarIndicator.getChildCount());
        indicator.setChecked(true);
    }


    @Override
    public void onPageScrollStateChanged(int state) {
    }


    /**
     * 设置 广告条点击事件
     *
     * @param l 事件监听器
     */
    public void setOnADBarClickListener(OnADBarClickListener l) {
        this.l = l;
    }

    private OnADBarClickListener l;

    public interface OnADBarClickListener {
        void onADBarClickListener(int position);
    }

    float x, y;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                if (event.getX() == x && y == event.getY()) {
                    if (l != null) {
                        l.onADBarClickListener(adapter.getPosition(mADBarView.getCurrentItem()));
                    }
                }
                break;
        }
        return false;
    }

}

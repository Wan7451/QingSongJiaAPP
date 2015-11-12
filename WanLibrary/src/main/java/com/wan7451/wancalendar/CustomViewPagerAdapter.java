package com.wan7451.wancalendar;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewPagerAdapter<V extends View> extends PagerAdapter {

    private V[] views;


    public CustomViewPagerAdapter(V[] views) {
        super();
        this.views = views;
    }


    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        if (arg0.getChildCount() == views.length) {
            arg0.removeView(views[arg1 % views.length]);
        }
        arg0.addView(views[arg1 % views.length], 0);

        return views[arg1 % views.length];
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
    }


    public V[] getAllItems() {
        return views;
    }
}

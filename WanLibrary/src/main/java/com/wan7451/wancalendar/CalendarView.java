package com.wan7451.wancalendar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wan7451.wanadapter.mylibrary.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2015/10/29.
 */
public class CalendarView extends LinearLayout implements DateView.CallBack {

    private DateView[] views;
    private TextView month;
    private ViewPager container;
    private CustomViewPagerAdapter<DateView> viewPagerAdapter;

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_wancalendar_view, this, false);
        month = (TextView) view.findViewById(R.id.wancalendar_month);
        month.setText(DateUtil.getYear() + "年" + (DateUtil.getMonth() > 9 ? "" : "0") + DateUtil.getMonth() + "月");
        container = (ViewPager) view.findViewById(R.id.wancalendar_container);
        views = new CalendarViewBuilder().createMassCalendarViews(getContext(), 5, this);
        viewPagerAdapter = new CustomViewPagerAdapter<DateView>(views);
        container.setAdapter(viewPagerAdapter);
        container.setCurrentItem(498);
        container.addOnPageChangeListener(new DateViewPagerLisenter(viewPagerAdapter));
        addView(view);
    }

    public void setHintDate(ArrayList<Calendar> data) {
        for (int i = 0; i < views.length; i++) {
            views[i].setHintData(data);
        }
    }


    @Override
    public void clickDate(CustomDate date) {

    }

    @Override
    public void onMesureCellHeight(int cellSpace) {

    }


    public void goCurrentDay() {
        views[container.getCurrentItem() % views.length].backToday();
    }

    @Override
    public void changeDate(CustomDate date) {
        month.setText(date.year + "年" + (date.getMonth() > 9 ? "" : "0") + date.month + "月");
    }
}

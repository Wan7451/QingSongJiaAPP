package com.wan7451.horizontalcalendar;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wan7451.wanadapter.mylibrary.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 水平日历
 * Created by Wan7451 on 2015/7/9.
 */
public class HorizaontalCalendar extends LinearLayout {

    private List<CalendarTime> mData;
    private Calendar calendar;
    private int data;
    private int week;
    private int month;
    private int year;
    private TextView currSelectTime;
    private HorizontalScrollView scrollView;
    private LayoutInflater inflater;
    private LinearLayout container;
    private int with;
    private int currPosotion = 0;

    public HorizaontalCalendar(Context context) {
        super(context);
        init();
    }

    public HorizaontalCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflater = LayoutInflater.from(getContext());
        View mainView = inflater.inflate(R.layout.layout_calendar_view, null);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        data = calendar.get(Calendar.DAY_OF_MONTH);
        week = calendar.get(Calendar.DAY_OF_WEEK);
        month = calendar.get(Calendar.MONTH);


        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            getWeekDay();
            CalendarTime ct = new CalendarTime();
            ct.setDay(data);
            ct.setWeek(week);
            ct.setMonth(month);
            ct.setYear(year);
            ct.setTime(year + "年" + (month + 1) + "月" + data + "日" + "  周" + getWeekDay(week));
            mData.add(ct);
            data++;

        }

        scrollView = (HorizontalScrollView) mainView.findViewById(R.id.calendar_view);
        currSelectTime = (TextView) mainView.findViewById(R.id.calendar_time_view);
        container = (LinearLayout) mainView.findViewById(R.id.calendar_container);

        initItemViews();


        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mainView.setLayoutParams(lp);

//        scrollView.setOnTouchListener(new OnTouchListener() {
//                                          @Override
//                                          public boolean onTouch(View v, MotionEvent event) {
//                                              if (event.getAction() == MotionEvent.ACTION_UP) {
//                                                  //右划 加载后面的时间
//                                                  if (scrollView.getScrollX() + getWidth() >= container.getMeasuredWidth()) {
//                                                      int from = mData.size();
//                                                      for (int i = 0; i < 30; i++) {
//                                                          CalendarTime ct = new CalendarTime();
//                                                          ct.setDay(data);
//                                                          ct.setWeek(week);
//                                                          ct.setMonth(month);
//                                                          ct.setYear(year);
//                                                          ct.setTime(year + "年" + (month + 1) + "月" + data + "日" + "  周" + getWeekDay(week));
//                                                          mData.add(ct);
//                                                          data++;
//                                                          getWeekDay();
//                                                      }
//
//                                                      addItemViews(from);
//                                                      return true;
//                                                  }
//                                              }
//
//                                              return false;
//                                          }
//                                      }
//
//        );


        addView(mainView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setCurrentSelction(currPosotion);
            }
        }, 100);
    }


    private void initItemViews() {
        for (int i = 0; i < mData.size(); i++) {
            View v = getView(i);
            container.addView(v);
        }
    }


    private void addItemViews(int from) {
        for (int i = from; i < mData.size(); i++) {
            View v = getView(i);
            container.addView(v);
        }
    }


    private void addItemViewAtHeader(int size) {
        for (int i = 0; i < size; i++) {
            View v = getView(i);
            container.addView(v, 0);
        }
    }

    public List<CalendarTime> getTimeData() {
        return mData;
    }

    @NonNull
    private View getView(int i) {
        View v = inflater.inflate(R.layout.item_calendat_layout, null);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 0, 20, 0);
        lp.gravity = Gravity.CENTER;
        v.setLayoutParams(lp);

        TextView week = (TextView) v.findViewById(R.id.calendar_week);
        TextView day = (TextView) v.findViewById(R.id.calendar_day);
        View indicatorView = v.findViewById(R.id.calendar_indicator_bg);
        ImageView indicator = (ImageView) v.findViewById(R.id.calendar_indicator);

        CalendarTime ct = mData.get(i);

        week.setText(getWeekDay(ct.getWeek()));

        day.setText(ct.getDay() + "");

        if (ct.isShowIndicator()) {
            indicatorView.setBackgroundResource(R.drawable.indicator_big);
        } else {
            indicatorView.setBackgroundResource(0);
        }

        if (ct.isHasData()) {
            indicator.setImageResource(R.drawable.indicator_small);
        } else {
            indicator.setImageDrawable(null);
        }

        with = v.getLayoutParams().width;

        final int finalI = i;
        v.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentSelction(finalI);
                currPosotion = finalI;
                if (l != null) {
                    l.onTimeSelect(mData.get(finalI), finalI);
                }
            }
        });
        return v;
    }

    public void setCurrPosotion(int currPosotion) {
        setCurrentSelction(currPosotion);
        this.currPosotion = currPosotion;
    }

    /**
     * 返回当前选择的Position
     *
     * @return 当前选择的Position
     */
    public int getCurrPosotion() {
        return currPosotion;
    }

    /**
     * 显示的日期，不要手动set
     *
     * @return 显示信息
     */
    public List<CalendarTime> getData() {
        return mData;
    }

    /**
     * 设置选择日期的监听器
     */
    public void setOnTimeSelectListener(OnTimeSelectListener l) {
        this.l = l;
    }

    private OnTimeSelectListener l;

    public interface OnTimeSelectListener {
        void onTimeSelect(CalendarTime ct, int position);
    }


    private void getWeekDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, data);
        year = cal.get(Calendar.YEAR);
        data = cal.get(Calendar.DAY_OF_MONTH);
        week = cal.get(Calendar.DAY_OF_WEEK);
        month = cal.get(Calendar.MONTH);

    }


    private String getWeekDay(int day) {
        switch (day) {
            case 1:
                return "日";
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
        }
        return "";
    }

    private void setCurrentSelction(int i) {


        resumeState();
        CalendarTime ct = mData.get(i);
        ct.setIsShowIndicator(true);
        currSelectTime.setText(mData.get(i).getTime());

        int w = container.getChildAt(0).getWidth() + 40;

        int width = ((Activity) getContext()).getWindowManager().getDefaultDisplay().getWidth();


        scrollView.smoothScrollTo(w * (i - 3), 0);

        int count = container.getChildCount();
        for (int j = 0; j < count; j++) {
            ct = mData.get(j);
            View v = container.getChildAt(j);
            View indicatorView = v.findViewById(R.id.calendar_indicator_bg);
            ImageView indicator = (ImageView) v.findViewById(R.id.calendar_indicator);

            if (ct.isShowIndicator()) {
                indicatorView.setBackgroundResource(R.drawable.indicator_big);
            } else {
                indicatorView.setBackgroundResource(0);
            }

            if (ct.isHasData()) {
                indicator.setImageResource(R.drawable.indicator_small);
            } else {
                indicator.setImageDrawable(null);
            }

        }

    }

    private void resumeState() {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setIsShowIndicator(false);
        }
    }
}

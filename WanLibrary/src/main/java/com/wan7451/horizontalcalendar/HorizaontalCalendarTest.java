package com.wan7451.horizontalcalendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wan7451.wanadapter.mylibrary.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 水平日历
 * Created by Wan7451 on 2015/7/9.
 */
public class HorizaontalCalendarTest extends LinearLayout {

    private List<CalendarTime> mData;
    //    private Calendar instance;
    private Calendar calendar;
    private int data;
    private int week;
    private int month;
    private int year;
    private CalendarAdapter adapter;
    private TextView currSelectTime;
    private RecyclerView recycleView;
    private LayoutInflater inflater;
    private LinearLayoutManager mgr;

    public HorizaontalCalendarTest(Context context) {
        super(context);
        init();
    }

    public HorizaontalCalendarTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflater = LayoutInflater.from(getContext());
        View mainView = inflater.inflate(R.layout.layout_calendar_view_test, null);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        data = calendar.get(Calendar.DAY_OF_MONTH);
        week = calendar.get(Calendar.DAY_OF_WEEK);
        month = calendar.get(Calendar.MONTH);


        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CalendarTime ct = new CalendarTime();
            ct.setDay(data);
            ct.setWeek(week);
            ct.setTime(year + "年" + (month + 1) + "月" + data + "日" + "  周" + getWeekDay(week));
            mData.add(ct);
            data++;
            getWeekDay();
        }

        recycleView = (RecyclerView) mainView.findViewById(R.id.calendar_view);
        currSelectTime = (TextView) mainView.findViewById(R.id.calendar_time_view);


        mgr = new LinearLayoutManager(getContext()) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                super.smoothScrollToPosition(recyclerView, state, position);
            }

        };


        mgr.setOrientation(HORIZONTAL);


        recycleView.setLayoutManager(mgr);
        adapter = new CalendarAdapter();


        recycleView.setAdapter(adapter);

        setCurrentSelction(0);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mainView.setLayoutParams(lp);


        addView(mainView);
    }

    private void getWeekDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, data);
        year = cal.get(Calendar.YEAR);
        data = cal.get(Calendar.DAY_OF_MONTH);
        week = cal.get(Calendar.DAY_OF_WEEK);
        month = cal.get(Calendar.MONTH);

    }


    class CalendarAdapter extends RecyclerView.Adapter<CalendarHolder> {


        @Override
        public CalendarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.item_calendat_layout, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 0);
            lp.gravity = Gravity.CENTER;
            v.setLayoutParams(lp);
            return new CalendarHolder(v);
        }

        @Override
        public void onBindViewHolder(CalendarHolder holder, int position) {

            CalendarTime ct = mData.get(position);

            holder.week.setText(getWeekDay(ct.getWeek()));

            holder.day.setText(ct.getDay() + "");

            if (ct.isShowIndicator()) {
                holder.indicatorView.setBackgroundResource(R.drawable.indicator_big);
            } else {
                holder.indicatorView.setBackgroundResource(0);
            }

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }


    }

    class CalendarHolder extends RecyclerView.ViewHolder {

        TextView week;
        TextView day;
        View indicatorView;
        ImageView indicator;

        public CalendarHolder(final View itemView) {
            super(itemView);
            week = (TextView) itemView.findViewById(R.id.calendar_week);
            day = (TextView) itemView.findViewById(R.id.calendar_day);
            indicatorView = itemView.findViewById(R.id.calendar_indicator_bg);
            indicator = (ImageView) itemView.findViewById(R.id.calendar_indicator);
            itemView.setOnClickListener(new OnClickListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onClick(View v) {
                    setCurrentSelction(getAdapterPosition());
                    Log.i("======", v.getLeft() + "");
                    Log.i("======", recycleView.getWidth() + "");
                    //  Log.i("======", recycleView.getDrawingTime()+"");

                    int offset = mgr.findLastCompletelyVisibleItemPosition()
                            - mgr.findFirstCompletelyVisibleItemPosition();

                    Log.i("======", mgr.findLastCompletelyVisibleItemPosition() + "");
                    Log.i("======", mgr.findFirstCompletelyVisibleItemPosition() + "");
                    Log.i("======", offset + "");

                    //  recycleView.smoothScrollToPosition(Math.min(getAdapterPosition() + offset + 1, adapter.getItemCount()));
//                    recycleView.smoothScrollBy(-v.getLeft(), 0);
//                    recycleView.smoothScrollToPosition(getAdapterPosition());
                }
            });
        }

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
        mData.get(i).setIsShowIndicator(true);
        adapter.notifyDataSetChanged();
        currSelectTime.setText(mData.get(i).getTime());

    }

    private void resumeState() {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setIsShowIndicator(false);
        }
    }
}

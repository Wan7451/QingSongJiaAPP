package com.qingsongjia.qingsongjia.driverexam;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 模拟考试
 */
public class AnalogyTestActivity extends WanActivity implements ViewPager.OnPageChangeListener {


    @Bind(R.id.analogy_MainView)
    ViewPager analogyMainView;
    @Bind(R.id.analogy_subject)
    TextView analogySubject;
    @Bind(R.id.analogy_currProgress)
    TextView analogyCurrProgress;

    private int time = 40 * 60;
    private Timer timer;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setLeftIcon(R.drawable.icon_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setContentTitle("模拟考试");
        setRightText("40:00");
        AnalogyTestAdapter adapter = new AnalogyTestAdapter(getSupportFragmentManager());
        analogyMainView.setAdapter(adapter);
        analogyMainView.addOnPageChangeListener(this);

        analogySubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //提交
            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        showTime(time);
                    }
                });
            }
        }, 1000, 1000);

    }


    public void next() {

        int p = analogyMainView.getCurrentItem();
        if (p < 99)
            analogyMainView.setCurrentItem(p + 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

    }

    private void showTime(int time) {
        int m = time / 60;
        int s = time % 60;

        String min = "";
        if (m < 10) {
            min = "0" + m;
        } else {
            min = m + "";
        }
        String sec = "";
        if (s < 10) {
            sec = "0" + s;
        } else {
            sec = s + "";
        }

        setRightText(min + ":" + sec);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_analogy_test;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        analogyCurrProgress.setText(position + 1 + "/100");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class AnalogyTestAdapter extends FragmentStatePagerAdapter {


        ArrayList<ExamTestFragment> fragments;


        public AnalogyTestAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            fragments.add(new ExamTestFragment());
            fragments.add(new ExamTestFragment());
            fragments.add(new ExamTestFragment());
            fragments.add(new ExamTestFragment());
            fragments.add(new ExamTestFragment());
        }

        @Override
        public Fragment getItem(int position) {

            ExamTestFragment f = fragments.get(position % fragments.size());
//            f.fillView(new ExamSubject());
            return f;
        }

        @Override
        public int getCount() {
            return 100;
        }
    }
}

package com.qingsongjia.qingsongjia.driverexam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
 * 各种练习
 */
public class ExamTestActivity extends WanActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.analogy_MainView)
    ViewPager analogyMainView;
    @Bind(R.id.analogy_currProgress)
    TextView analogyCurrProgress;

    private Timer timer;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("练习");
        AnalogyTestAdapter adapter = new AnalogyTestAdapter(getSupportFragmentManager());
        analogyMainView.setAdapter(adapter);
        analogyMainView.addOnPageChangeListener(this);

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


    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_exam_test;
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

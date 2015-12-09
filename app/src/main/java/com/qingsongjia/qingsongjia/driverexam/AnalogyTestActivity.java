package com.qingsongjia.qingsongjia.driverexam;


import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.localdata.DBUtils;
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
    private int currPro;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setLeftIcon(R.drawable.icon_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setContentTitle("模拟考试");
        setRightText("40:00");
        AnalogyTestAdapter adapter = new AnalogyTestAdapter(getSupportFragmentManager());
        analogyMainView.setAdapter(adapter);
        analogyMainView.addOnPageChangeListener(this);

        //科目
        int type = getIntent().getIntExtra("type", 1);

        analogySubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //提交
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("提示")
                        .setMessage("您确定要交卷么?")
                        .setPositiveButton("交卷", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("交卷成功");
                                finish();
                            }
                        })
                        .setNegativeButton("不交卷", null)
                        .show();


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

        currPro = DBUtils.getLastTiMu(getContext());

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
            f.setPosition(currPro + position);
//            f.fillView(new ExamSubject());
            return f;
        }

        @Override
        public int getCount() {
            return 100;
        }
    }
}

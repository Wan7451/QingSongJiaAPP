package com.qingsongjia.qingsongjia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.qingsongjia.qingsongjia.driverexam.MainFragment;
import com.qingsongjia.qingsongjia.driverexam.SubjectFourFragment;
import com.qingsongjia.qingsongjia.driverexam.SubjectOneFragment;
import com.qingsongjia.qingsongjia.driverexam.SubjectThreeFragment;
import com.qingsongjia.qingsongjia.driverexam.SubjectTwoFragment;

import java.util.ArrayList;

/**
 * 科目练习
 * Created by wanggang on 15/11/14.
 */
public class ExamAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    String[] titles={"首页","科一","科二","科三","科四"};

    public ExamAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new MainFragment());
        fragments.add(new SubjectOneFragment());
        fragments.add(new SubjectTwoFragment());
        fragments.add(new SubjectThreeFragment());
        fragments.add(new SubjectFourFragment());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}

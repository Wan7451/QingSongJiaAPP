package com.qingsongjia.qingsongjia.driverschool;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qingsongjia.qingsongjia.bean.SchoolDetail;
import com.qingsongjia.qingsongjia.driverexam.SubjectFourFragment;
import com.qingsongjia.qingsongjia.driverexam.SubjectOneFragment;
import com.qingsongjia.qingsongjia.driverexam.SubjectThreeFragment;
import com.qingsongjia.qingsongjia.driverexam.SubjectTwoFragment;

import java.util.ArrayList;

/**
 * 科目练习
 * Created by wanggang on 15/11/14.
 */
public class SchoolEvaluateAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    String[] titles={"全部点评","本校学员"};

    public SchoolEvaluateAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new AllSchoolEvaluateFragment());
        fragments.add(new SchoolEvaluateFragment());
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

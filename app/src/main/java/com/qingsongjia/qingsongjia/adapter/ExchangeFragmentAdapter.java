package com.qingsongjia.qingsongjia.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qingsongjia.qingsongjia.exchange.ExchangeFourFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeItemFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeOneFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeShareFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeThreeFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeTwoFragment;
import com.qingsongjia.qingsongjia.fragment.ExchangeFragment;

import java.util.ArrayList;

/**
 * 交流中心
 * Created by wanggang on 15/12/8.
 */
public class ExchangeFragmentAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    String[] titles={"科一","科二","科三","科四","拼车去考试","拼车去练习","拼车去旅游","经验分享","路况分享","交友娱乐"};

    public ExchangeFragmentAdapter(FragmentManager fm) {
        super(fm);
        for (int i = 0; i < titles.length; i++) {
            ExchangeItemFragment fragment = new ExchangeItemFragment();
            Bundle bundle=new Bundle();
            bundle.putString("type",titles[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
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

package com.qingsongjia.qingsongjia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qingsongjia.qingsongjia.exchange.ExchangeFourFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeOneFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeShareFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeThreeFragment;
import com.qingsongjia.qingsongjia.exchange.ExchangeTwoFragment;

import java.util.ArrayList;

/**
 * 交流中心
 * Created by wanggang on 15/12/8.
 */
public class ExchangeFragmentAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    String[] titles={"科一","科二","科三","科四","经验分享"};

    public ExchangeFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new ExchangeOneFragment());
        fragments.add(new ExchangeTwoFragment());
        fragments.add(new ExchangeThreeFragment());
        fragments.add(new ExchangeFourFragment());
        fragments.add(new ExchangeShareFragment());
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

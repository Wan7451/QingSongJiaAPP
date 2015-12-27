package com.qingsongjia.qingsongjia.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qingsongjia.qingsongjia.exchange.ExchangeItemFragment;

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

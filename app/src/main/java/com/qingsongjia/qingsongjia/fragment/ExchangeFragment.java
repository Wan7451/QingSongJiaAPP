package com.qingsongjia.qingsongjia.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.qingsongjia.qingsongjia.adapter.ExchangeFragmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExchangeFragment extends Fragment {

    @Bind(R.id.exchange_navigation_tab)
    TabLayout examNavigationTab;
    @Bind(R.id.exchange_navigation_view)
    ViewPager examNavigationView;

    public ExchangeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exchange, container, false);
        ButterKnife.bind(this, v);

        final ExchangeFragmentAdapter adapter=new ExchangeFragmentAdapter(getChildFragmentManager());
        examNavigationTab.setTabsFromPagerAdapter(adapter);
        examNavigationView.setAdapter(adapter);
        examNavigationTab.setTabsFromPagerAdapter(adapter);
        examNavigationTab.setupWithViewPager(examNavigationView);
        examNavigationTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        examNavigationTab.setTabGravity(TabLayout.GRAVITY_FILL);

        ((App)getContext().getApplicationContext()).setExchangeDri_type(adapter.getPageTitle(0).toString());

        examNavigationView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((App)getContext().getApplicationContext()).setExchangeDri_type(adapter.getPageTitle(position).toString());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return v;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

package com.qingsongjia.qingsongjia.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;



public class ExamFragment extends Fragment  {

    @Bind(R.id.exam_navigation_tab)
    TabLayout examNavigationTab;
    @Bind(R.id.exam_navigation_view)
    ViewPager examNavigationView;

    public ExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exam, container, false);
        ButterKnife.bind(this, v);

        ExamAdapter adapter=new ExamAdapter(getChildFragmentManager());
        examNavigationTab.setTabsFromPagerAdapter(adapter);
        examNavigationView.setAdapter(adapter);
        examNavigationTab.setTabsFromPagerAdapter(adapter);
        examNavigationTab.setupWithViewPager(examNavigationView);
        examNavigationTab.setTabMode(TabLayout.MODE_FIXED);
        examNavigationTab.setTabGravity(TabLayout.GRAVITY_FILL);
        return v;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

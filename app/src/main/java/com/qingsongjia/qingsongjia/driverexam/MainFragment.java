package com.qingsongjia.qingsongjia.driverexam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.qingsongjia.qingsongjia.yuekao.InquiryExamActivity;
import com.wan7451.adbar.ADBarView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @Bind(R.id.sunbjectOne_AdView)
    ADBarView sunbjectOneAdView;
    @Bind(R.id.main_zjx)
    LinearLayout mainZjx;
    @Bind(R.id.main_yyks)
    LinearLayout mainYyks;
    @Bind(R.id.main_yyxl)
    LinearLayout mainYyxl;
    @Bind(R.id.main_qcwxby)
    LinearLayout mainQcwxby;
    @Bind(R.id.main_qcbx)
    LinearLayout mainQcbx;
    @Bind(R.id.main_wdsc)
    LinearLayout mainWdsc;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        ArrayList<String> urls=new ArrayList<>();
        urls.add(LocalPreference.getTopImagePath(getContext()));
        sunbjectOneAdView.setShowURLs(urls);

        //找驾校
        mainZjx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startAllSchoolList(getContext());
            }
        });
        //预约练习
        mainYyxl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startInquiryTraining(getContext());
            }
        });
        //预约考试
        mainYyks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //预约考试
                UIManager.startInquiryExam(getContext(), InquiryExamActivity.INQUIRY_TYPE_ONE);
            }
        });
        //汽车维修保养
        mainQcwxby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //汽车保险
        mainQcbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //我的商城
        mainWdsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

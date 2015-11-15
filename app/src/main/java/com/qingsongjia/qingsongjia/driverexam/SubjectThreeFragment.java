package com.qingsongjia.qingsongjia.driverexam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageTextAdapter;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.wan7451.adbar.ADBarView;
import com.wan7451.base.WanFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectThreeFragment extends WanFragment {


    @Bind(R.id.sunbjectThree_AdView)
    ADBarView sunbjectThreeAdView;
    @Bind(R.id.sunbjectThree_ksjq)
    TextView sunbjectThreeKsjq;
    @Bind(R.id.sunbjectThree_views)
    GridView sunbjectThreeViews;
    @Bind(R.id.sunbjectThreee_others)
    GridView sunbjectThreeeOthers;

    public SubjectThreeFragment() {
        // Required empty public constructor
    }



    @Override
    protected boolean isShowTitleView() {
        return false;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        ArrayList<String> urls=new ArrayList<>();
        urls.add("http://h.hiphotos.baidu.com/image/pic/item/4ec2d5628535e5dd2820232370c6a7efce1b623a.jpg");
        sunbjectThreeAdView.setShowURLs(urls);

        ArrayList<ExamImageText> data=new ArrayList<>();
        data.add(new ExamImageText(R.drawable.icon_exam_cjpd,"车距判断"));
        data.add(new ExamImageText(R.drawable.icon_exam_dwcz,"档位操作"));
        data.add(new ExamImageText(R.drawable.icon_exam_dg,"灯光"));
        data.add(new ExamImageText(R.drawable.icon_exam_zx,"直行"));
        ExamImageTextAdapter adapter=new ExamImageTextAdapter(getContext(),data,R.layout.item_exam_imagetext);
        sunbjectThreeViews.setAdapter(adapter);


        ArrayList<ExamImageText> data2=new ArrayList<>();
        data2.add(new ExamImageText(R.drawable.icon_exam_yyks,"预约考试"));
        data2.add(new ExamImageText(R.drawable.icon_exam_yylc,"预约教练"));
        ExamImageTextAdapter adapter2=new ExamImageTextAdapter(getContext(),data2,R.layout.item_exam_imagetext);
        sunbjectThreeeOthers.setAdapter(adapter2);

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.fragment_subject_three;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

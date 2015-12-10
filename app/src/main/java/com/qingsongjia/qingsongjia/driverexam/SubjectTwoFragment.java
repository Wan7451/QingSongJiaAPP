package com.qingsongjia.qingsongjia.driverexam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageTextAdapter;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.adbar.ADBarView;
import com.wan7451.base.WanFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectTwoFragment extends WanFragment implements AdapterView.OnItemClickListener {


    @Bind(R.id.sunbjectTwo_AdView)
    ADBarView sunbjectTwoAdView;
    @Bind(R.id.sunbjectTwo_ksjq)
    TextView sunbjectTwoKsjq;
    @Bind(R.id.sunbjectTwo_views)
    GridView sunbjectTwoViews;
    @Bind(R.id.sunbjectTwo_others)
    GridView sunbjectTwoOthers;

    public SubjectTwoFragment() {
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
        urls.add(LocalPreference.getTopImagePath(getContext()));
        sunbjectTwoAdView.setShowURLs(urls);

        ArrayList<ExamImageText> data=new ArrayList<>();
        data.add(new ExamImageText(R.drawable.icon_exam_seatbelt,"安全带"));
        data.add(new ExamImageText(R.drawable.icon_exam_dhkg,"点火开关"));
        data.add(new ExamImageText(R.drawable.icon_exam_fxp,"方向盘"));
        data.add(new ExamImageText(R.drawable.icon_exam_lhq,"离合器"));
        data.add(new ExamImageText(R.drawable.icon_exam_jstb,"加速踏板"));
        data.add(new ExamImageText(R.drawable.icon_exam_zxzd,"驻车制动"));
        data.add(new ExamImageText(R.drawable.icon_exam_zytz,"座椅调整"));
        data.add(new ExamImageText(R.drawable.icon_exam_hsj,"后视镜"));
        ExamImageTextAdapter adapter=new ExamImageTextAdapter(getContext(),data,R.layout.item_exam_imagetext);
        sunbjectTwoViews.setAdapter(adapter);
        sunbjectTwoViews.setOnItemClickListener(this);

        ArrayList<ExamImageText> data2=new ArrayList<>();
        data2.add(new ExamImageText(R.drawable.icon_exam_yyks,"预约考试"));
        data2.add(new ExamImageText(R.drawable.icon_exam_yylc,"预约教练"));
        ExamImageTextAdapter adapter2=new ExamImageTextAdapter(getContext(),data2,R.layout.item_exam_imagetext);
        sunbjectTwoOthers.setAdapter(adapter2);


        sunbjectTwoOthers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               switch (i){
                   case 0:
                       UIManager.startInquiryExam(getContext(), InquiryExamActivity.INQUIRY_TYPE_TWO);
                       break;
                   case 1:
                       UIManager.startInquiryTraining(getContext());
                       break;
               }
            }
        });


    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.fragment_subject_two;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                UIManager.startExamDetail(getContext(),ExamDetailActivity.DETAIL_TYPE_AQD);
                break;
            case 1:
                UIManager.startExamDetail(getContext(),ExamDetailActivity.DETAIL_TYPE_DHKG);
                break;
            case 2:
                UIManager.startExamDetail(getContext(),ExamDetailActivity.DETAIL_TYPE_FXP);
                break;
            case 3:
                UIManager.startExamDetail(getContext(),ExamDetailActivity.DETAIL_TYPE_LHQ);
                break;
            case 4:
                UIManager.startExamDetail(getContext(),ExamDetailActivity.DETAIL_TYPE_JSTB);
                break;
            case 5:
                UIManager.startExamDetail(getContext(),ExamDetailActivity.DETAIL_TYPE_ZCZD);
                break;
            case 6:
                UIManager.startExamDetail(getContext(),ExamDetailActivity.DETAIL_TYPE_ZYTZ);
                break;
            case 7:
                UIManager.startExamDetail(getContext(),ExamDetailActivity.DETAIL_TYPE_HSJ);
                break;
        }

    }
}

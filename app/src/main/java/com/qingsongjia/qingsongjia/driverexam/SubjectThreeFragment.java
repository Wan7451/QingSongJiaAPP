package com.qingsongjia.qingsongjia.driverexam;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageTextAdapter;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.qingsongjia.qingsongjia.yuekao.InquiryExamActivity;
import com.wan7451.adbar.ADBarView;
import com.wan7451.base.WanFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectThreeFragment extends WanFragment implements AdapterView.OnItemClickListener {


    @Bind(R.id.sunbjectThree_AdView)
    ADBarView sunbjectThreeAdView;
    @Bind(R.id.sunbjectThree_ksjq)
    TextView sunbjectThreeKsjq;
    @Bind(R.id.sunbjectThree_views)
    GridView sunbjectThreeViews;
    @Bind(R.id.sunbjectThreee_others)
    GridView sunbjectThreeeOthers;
    private ArrayList<ExamImageText> data;

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
        urls.add(LocalPreference.getTopImagePath(getContext()));
        sunbjectThreeAdView.setShowURLs(urls);

        data = new ArrayList<>();
        data.add(new ExamImageText(R.drawable.icon_exam_cjpd,"车距判断"));
        data.add(new ExamImageText(R.drawable.icon_exam_dwcz,"档位操作"));
        data.add(new ExamImageText(R.drawable.icon_exam_dg, "灯光"));
        data.add(new ExamImageText(R.drawable.icon_exam_zx, "直行"));
        ExamImageTextAdapter adapter=new ExamImageTextAdapter(getContext(), data,R.layout.item_exam_imagetext);
        sunbjectThreeViews.setAdapter(adapter);
        sunbjectThreeViews.setOnItemClickListener(this);


        ArrayList<ExamImageText> data2=new ArrayList<>();
        data2.add(new ExamImageText(R.drawable.icon_exam_yyks,"预约考试"));
        data2.add(new ExamImageText(R.drawable.icon_exam_yylc,"预约教练"));
        ExamImageTextAdapter adapter2=new ExamImageTextAdapter(getContext(),data2,R.layout.item_exam_imagetext);
        sunbjectThreeeOthers.setAdapter(adapter2);

        sunbjectThreeeOthers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        UIManager.startInquiryExam(getContext(), InquiryExamActivity.INQUIRY_TYPE_THREE);
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
        return R.layout.fragment_subject_three;
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
                UIManager.startExamDetail(getContext(),data.get(i).getShowText(), ExamDetailActivity.DETAIL_TYPE_CJPD);
                break;
            case 1:
                UIManager.startExamDetail(getContext(),data.get(i).getShowText(),ExamDetailActivity.DETAIL_TYPE_DWCZ);
                break;
            case 2:
                UIManager.startExamDetail(getContext(),data.get(i).getShowText(),ExamDetailActivity.DETAIL_TYPE_DG);
                break;
            case 3:
                UIManager.startExamDetail(getContext(),data.get(i).getShowText(),ExamDetailActivity.DETAIL_TYPE_ZX);
                break;
        }
    }
}

package com.qingsongjia.qingsongjia.fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageTextAdapter;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.adbar.ADBarView;
import com.wan7451.base.WanFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 教练端 发布陪练
 */
public class JiaoLianToolsFragment extends WanFragment implements AdapterView.OnItemClickListener {


    @Bind(R.id.sunbjectOne_AdView)
    ADBarView sunbjectOneAdView;
    @Bind(R.id.sunbjectTools_views)
    GridView sunbjectToolsViews;


    @Override
    protected boolean isShowTitleView() {
        return false;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        ArrayList<String> urls = new ArrayList<>();
        urls.add(LocalPreference.getTopImagePath(getContext()));
        sunbjectOneAdView.setShowURLs(urls);

        ArrayList<ExamImageText> data = new ArrayList<>();
        data.add(new ExamImageText(R.drawable.icon_exam_seatbelt, "陪驾"));
        ExamImageTextAdapter adapter = new ExamImageTextAdapter(getContext(), data, R.layout.item_exam_imagetext);
        sunbjectToolsViews.setAdapter(adapter);
        sunbjectToolsViews.setOnItemClickListener(this);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.fragment_tools;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //dri_type 0 :学生 1 教练
        User u = LocalPreference.getCurrentUser(getContext());
        int type = 0;
        if (!TextUtils.isEmpty(u.getDri_type()) && (
                !u.getDri_type().endsWith("0"))) {
            type = 1;
        }
        if (type == 0) {
            //学员
            switch (i) {
                case 0:
                    UIManager.startPeiLianList(getContext());
                    break;
            }
        } else {
            //教练
            switch (i) {
                case 0:
                    UIManager.startPushOrder(getContext());
                    break;
            }
        }


    }
}

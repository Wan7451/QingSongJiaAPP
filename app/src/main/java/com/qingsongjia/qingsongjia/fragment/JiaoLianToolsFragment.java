package com.qingsongjia.qingsongjia.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageTextAdapter;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.adbar.ADBarView;
import com.wan7451.base.WanFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
/**教练端 发布陪练
 */
public class JiaoLianToolsFragment extends WanFragment implements AdapterView.OnItemClickListener {


    @Bind(R.id.sunbjectOne_AdView)
    ADBarView sunbjectOneAdView;
    @Bind(R.id.sunbjectTools_views)
    GridView sunbjectToolsViews;

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        ArrayList<String> urls=new ArrayList<>();
        urls.add("http://h.hiphotos.baidu.com/image/pic/item/4ec2d5628535e5dd2820232370c6a7efce1b623a.jpg");
        sunbjectOneAdView.setShowURLs(urls);

        ArrayList<ExamImageText> data=new ArrayList<>();
        data.add(new ExamImageText(R.drawable.icon_exam_seatbelt,"陪驾"));
        ExamImageTextAdapter adapter=new ExamImageTextAdapter(getContext(),data,R.layout.item_exam_imagetext);
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
        switch (i){
            case 0:
                UIManager.startPushOrder(getContext());
                break;
        }
    }
}

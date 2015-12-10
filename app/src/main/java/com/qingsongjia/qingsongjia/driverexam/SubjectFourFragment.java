package com.qingsongjia.qingsongjia.driverexam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageTextAdapter;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.adbar.ADBarView;
import com.wan7451.base.WanFragment;
import com.wan7451.radiamenu.RadialMenuItem;
import com.wan7451.radiamenu.RadialMenuWidget;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubjectFourFragment extends WanFragment {


    @Bind(R.id.sunbjectFour_AdView)
    ADBarView sunbjectFourAdView;
    @Bind(R.id.sunbjectFour_radiaMenu)
    RadialMenuWidget pieMenu;
    @Bind(R.id.sunbjectFour_views)
    GridView sunbjectFourViews;

    private RadialMenuItem testItem;
    private RadialMenuItem orderItem;
    private RadialMenuItem chapterItem;
    private RadialMenuItem specialItem;
    private RadialMenuItem errorItem;
    private RadialMenuItem randomItem;

    public SubjectFourFragment() {
    }

    @Override
    protected boolean isShowTitleView() {
        return false;
    }

    @Override
    protected void initView(View v) {
        ButterKnife.bind(this, v);

        ArrayList<String> urls=new ArrayList<>();
        urls.add(LocalPreference.getTopImagePath(getContext()));
        sunbjectFourAdView.setShowURLs(urls);

        testItem = new RadialMenuItem("test", "模拟考试");
        testItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                showToast("模拟考试");
            }
        });
        testItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                UIManager.startAnalogyExam(getContext(),4);
            }
        });
        pieMenu.setCenterCircle(testItem);

        orderItem = new RadialMenuItem("order", "顺序练习");
        orderItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                UIManager.startExamTest(getContext(),ExamTestActivity.TYPE_NORMAL);
            }
        });
        pieMenu.addMenuEntry(orderItem);

        chapterItem = new RadialMenuItem("chapter", "章节练习");
        chapterItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                UIManager.startZhangJieTest(getContext());
            }
        });
        pieMenu.addMenuEntry(chapterItem);

        specialItem = new RadialMenuItem("special", "专项练习");
        specialItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                UIManager.startZhuanXiangTest(getContext());
            }
        });
        pieMenu.addMenuEntry(specialItem);

        errorItem = new RadialMenuItem("error", "错题练习");
        errorItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                UIManager.startExamTest(getContext(),ExamTestActivity.TYPE_WRONG);
            }
        });
        pieMenu.addMenuEntry(errorItem);

        randomItem = new RadialMenuItem("random", "随机练习");
        randomItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                UIManager.startExamTest(getContext(),ExamTestActivity.TYPE_RANDOM);
            }
        });
        pieMenu.addMenuEntry(randomItem);


        ArrayList<ExamImageText> data = new ArrayList<>();
        data.add(new ExamImageText(R.drawable.icon_exam_bmxz, "报名须知"));
        data.add(new ExamImageText(R.drawable.icon_exam_jtfg, "交通法规"));
        data.add(new ExamImageText(R.drawable.icon_exam_jtbz, "交通标志"));
        data.add(new ExamImageText(R.drawable.icon_exam_xssl, "新手上路"));
        ExamImageTextAdapter adapter = new ExamImageTextAdapter(getContext(), data, R.layout.item_exam_imagetext);
        sunbjectFourViews.setAdapter(adapter);
        sunbjectFourViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
//                    case 0:
//                        UIManager.startWebView(getContext(), WebActivity.TYPE_BMXZ);
//                        break;
//                    case 1:
//                        UIManager.startWebView(getContext(), WebActivity.TYPE_JTFG);
//                        break;
//                    case 2:
//                        UIManager.startJiaoTongBiaoZhi(getContext());
//                        break;
//                    case 3:
//                        UIManager.startWebView(getContext(), WebActivity.TYPE_XSSL);
//                        break;
                }
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.fragment_subject_four;
    }


    public void showToast(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

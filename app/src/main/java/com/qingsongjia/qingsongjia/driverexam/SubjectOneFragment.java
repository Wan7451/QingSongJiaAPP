package com.qingsongjia.qingsongjia.driverexam;


import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.adbar.ADBarView;
import com.wan7451.base.WanActivity;
import com.wan7451.radiamenu.RadialMenuItem;
import com.wan7451.radiamenu.RadialMenuWidget;
import com.wan7451.base.WanFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectOneFragment extends WanFragment {


    @Bind(R.id.sunbjectOne_AdView)
    ADBarView sunbjectOneAdView;
    @Bind(R.id.sunbjectOne_radiaMenu)
    RadialMenuWidget pieMenu;
    @Bind(R.id.sunbjectOne_views)
    GridView sunbjectOneViews;

    private RadialMenuItem testItem;
    private RadialMenuItem orderItem;
    private RadialMenuItem chapterItem;
    private RadialMenuItem specialItem;
    private RadialMenuItem errorItem;
    private RadialMenuItem randomItem;

    public SubjectOneFragment() {
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
        sunbjectOneAdView.setShowURLs(urls);

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
                UIManager.startAnalogyExam(getContext(),1);
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
        sunbjectOneViews.setAdapter(adapter);
        sunbjectOneViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        UIManager.startItemList(getContext(), ItemListActivity.TYPE_BMXZ);
                        break;
                    case 1:
                        UIManager.startItemList(getContext(), ItemListActivity.TYPE_BMXZ);
                        break;
                    case 2:
                        UIManager.startJiaoTongBiaoZhi(getContext());
                        break;
                    case 3:
                        UIManager.startItemList(getContext(), ItemListActivity.TYPE_BMXZ);
                        break;
                }
            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.fragment_subject_one;
    }


    public void showToast(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(EventData data) {
        if(data.getType()==EventData.TYPE_CHANGETOP){

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

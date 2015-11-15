package com.qingsongjia.qingsongjia.driverexam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.advancedview.RadialMenuItem;
import com.wan7451.advancedview.RadialMenuWidget;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectOneFragment extends Fragment {


    private RadialMenuWidget pieMenu;
    private RadialMenuItem testItem;
    private RadialMenuItem orderItem;
    private RadialMenuItem chapterItem;
    private RadialMenuItem specialItem;
    private RadialMenuItem errorItem;
    private RadialMenuItem randomItem;

    public SubjectOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_subject_one, container, false);

        pieMenu = (RadialMenuWidget) v.findViewById(R.id.sunbjectOne_radiaMenu);
        testItem = new RadialMenuItem("test","模拟考试");
        testItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                showToast("模拟考试");
            }
        });
        pieMenu.setCenterCircle(testItem);

        orderItem =new RadialMenuItem("order","顺序练习");
        orderItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                showToast("顺序练习");
            }
        });
        pieMenu.addMenuEntry(orderItem);

        chapterItem =new RadialMenuItem("chapter","章节练习");
        chapterItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                showToast("章节练习");
            }
        });
        pieMenu.addMenuEntry(chapterItem);

        specialItem =new RadialMenuItem("special","专项练习");
        specialItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                showToast("专项练习");
            }
        });
        pieMenu.addMenuEntry(specialItem);

        errorItem =new RadialMenuItem("error","错题练习");
        errorItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                showToast("错题练习");
            }
        });
        pieMenu.addMenuEntry(errorItem);

        randomItem =new RadialMenuItem("random","随机练习");
        randomItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                showToast("随机练习");
            }
        });
        pieMenu.addMenuEntry(randomItem);

//        pieMenu.setCenterCircleRadius(50);
//        pieMenu.setOuterRingRadius(100, 200);
//
//
//        pieMenu.setOutlineColor(0x07BB9B, 225); //外线颜色
//        pieMenu.setInnerRingColor(0xFFFFFF, 225);
//        pieMenu.setOuterRingColor(0xFFFFFF, 225);

        return v;
    }


    public void showToast(String toast){
        Toast.makeText(getContext(), toast,Toast.LENGTH_SHORT).show();
    }
}

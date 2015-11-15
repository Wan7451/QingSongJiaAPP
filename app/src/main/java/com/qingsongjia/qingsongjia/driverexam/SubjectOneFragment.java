package com.qingsongjia.qingsongjia.driverexam;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingsongjia.qingsongjia.R;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;

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

        pieMenu = new RadialMenuWidget(getContext());
        testItem = new RadialMenuItem("test","模拟考试");
        testItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {

            }
        });
        pieMenu.setCenterCircle(testItem);

        orderItem =new RadialMenuItem("order","顺序练习");
        pieMenu.addMenuEntry(orderItem);

        chapterItem =new RadialMenuItem("chapter","顺序练习");
        pieMenu.addMenuEntry(chapterItem);

        specialItem =new RadialMenuItem("special","专项练习");
        pieMenu.addMenuEntry(specialItem);

        errorItem =new RadialMenuItem("error","错题练习");
        pieMenu.addMenuEntry(errorItem);

        randomItem =new RadialMenuItem("random","随机练习");
        pieMenu.addMenuEntry(randomItem);


        pieMenu.setAnimationSpeed(0L);
        pieMenu.setSourceLocation(0, 0);
        pieMenu.setCenterCircleRadius(40);
        pieMenu.setTextSize(15);
        pieMenu.setOutlineColor(0x07BB9B, 225);
        pieMenu.setInnerRingColor(0x07BB9B, 225);
        pieMenu.setOuterRingColor(0xFFFFFF, 225);
        pieMenu.setSelectedColor(0x07BB9B,255);
        pieMenu.show(v);


        return v;
    }

}

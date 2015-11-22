package com.qingsongjia.qingsongjia.driverexam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.ExamSubject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamTestFragment extends Fragment {


    @Bind(R.id.examTest_title)
    TextView examTestTitle;
    @Bind(R.id.examTest_titleImage)
    ImageView examTestTitleImage;
    @Bind(R.id.examTest_itemAIcon)
    ImageView examTestItemAIcon;
    @Bind(R.id.examTest_itemAText)
    TextView examTestItemAText;
    @Bind(R.id.examTest_itemA)
    LinearLayout examTestItemA;
    @Bind(R.id.examTest_itemBIcon)
    ImageView examTestItemBIcon;
    @Bind(R.id.examTest_itemBText)
    TextView examTestItemBText;
    @Bind(R.id.examTest_itemB)
    LinearLayout examTestItemB;
    @Bind(R.id.examTest_itemCIcon)
    ImageView examTestItemCIcon;
    @Bind(R.id.examTest_itemCText)
    TextView examTestItemCText;
    @Bind(R.id.examTest_itemC)
    LinearLayout examTestItemC;
    @Bind(R.id.examTest_itemDIcon)
    ImageView examTestItemDIcon;
    @Bind(R.id.examTest_itemDText)
    TextView examTestItemDText;
    @Bind(R.id.examTest_itemD)
    LinearLayout examTestItemD;
    @Bind(R.id.examTest_itemSubject)
    Button examTestItemSubject;

    public ExamTestFragment() {
    }


    private ExamSubject data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_test, container, false);
        ButterKnife.bind(this, view);

        examTestItemSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity() instanceof  AnalogyTestActivity){
                    ((AnalogyTestActivity) getActivity()).next();
                }
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (data != null)
            fillView(data);
        else
            fillView(null);

    }

    private boolean isChoiceA, isChoiceB, isChoiceC, isChoiceD;

    public void fillView(ExamSubject subject) {
        examTestTitle.setText("题目");
        examTestItemAText.setText("选项A");
        examTestItemBText.setText("选项B");
        examTestItemCText.setText("选项C");
        examTestItemDText.setText("选项D");

        isChoiceA = isChoiceB = isChoiceC = isChoiceD = false;
        examTestItemA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isChoiceA = !isChoiceA;

                if (isChoiceA)
                    examTestItemAIcon.setImageResource(R.drawable.icon_exam_a_selected);
                else
                    examTestItemAIcon.setImageResource(R.drawable.icon_exam_a_normal);
            }
        });

        examTestItemB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isChoiceB = !isChoiceB;

                if (isChoiceB)
                    examTestItemBIcon.setImageResource(R.drawable.icon_exam_b_selected);
                else
                    examTestItemBIcon.setImageResource(R.drawable.icon_exam_b_normal);
            }
        });

        examTestItemC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isChoiceC = !isChoiceC;

                if (isChoiceC)
                    examTestItemCIcon.setImageResource(R.drawable.icon_exam_c_selected);
                else
                    examTestItemCIcon.setImageResource(R.drawable.icon_exam_c_normal);
            }
        });

        examTestItemD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isChoiceD = !isChoiceD;

                if (isChoiceD)
                    examTestItemDIcon.setImageResource(R.drawable.icon_exam_d_selected);
                else
                    examTestItemDIcon.setImageResource(R.drawable.icon_exam_d_normal);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public ExamSubject getData() {
        return data;
    }

    public void setData(ExamSubject data) {
        this.data = data;
    }
}

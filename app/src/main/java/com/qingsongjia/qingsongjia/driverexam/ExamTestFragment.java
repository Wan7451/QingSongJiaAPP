package com.qingsongjia.qingsongjia.driverexam;


import android.database.Cursor;
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
import com.qingsongjia.qingsongjia.localdata.DBUtils;

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
    @Bind(R.id.examTest_answer)
    TextView examTestAnswer;
    private Cursor cursor;
    private int type;

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
                if (getActivity() instanceof AnalogyTestActivity) {
                    ((AnalogyTestActivity) getActivity()).next();
                }
                if (getActivity() instanceof ExamTestActivity) {
                    ((ExamTestActivity) getActivity()).next();
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

    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    private boolean isChoiceA, isChoiceB, isChoiceC, isChoiceD;

    public void fillView(ExamSubject subject) {


        cursor = DBUtils.getCurrentTiMu(getContext(), position+1);

        if(cursor.getCount()==0)
            return;

        cursor.moveToNext();
        String question = cursor.getString(cursor.getColumnIndex("Question"));
        final String answerTrue = cursor.getString(cursor.getColumnIndex("AnswerTrue"));
        String explain = cursor.getString(cursor.getColumnIndex("explain"));
        examTestAnswer.setText(explain);

        examTestTitle.setText(question);

        type = cursor.getInt(cursor.getColumnIndex("Type"));
        switch (type) {
            case 1: //判断
                examTestItemAText.setText("对");
                examTestItemBText.setText("错");
                examTestItemC.setVisibility(View.GONE);
                examTestItemD.setVisibility(View.GONE);

                examTestItemSubject.setVisibility(View.GONE);
                break;
            case 2://单选
            case 3://多选
                String an1 = cursor.getString(cursor.getColumnIndex("An1"));
                String an2 = cursor.getString(cursor.getColumnIndex("An2"));
                String an3 = cursor.getString(cursor.getColumnIndex("An3"));
                String an4 = cursor.getString(cursor.getColumnIndex("An4"));
                examTestItemAText.setText(an1);
                examTestItemBText.setText(an2);
                examTestItemCText.setText(an3);
                examTestItemDText.setText(an4);
                if (type == 2) {
                    examTestItemSubject.setVisibility(View.GONE);
                }
                break;

        }

        isChoiceA = isChoiceB = isChoiceC = isChoiceD = false;
        examTestItemA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChoiceA = !isChoiceA;
                if (isChoiceA) {
                    examTestItemAIcon.setImageResource(R.drawable.icon_exam_a_selected);
                } else {
                    examTestItemAIcon.setImageResource(R.drawable.icon_exam_a_normal);
                }
                handerAns(1, answerTrue);
            }
        });

        examTestItemB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChoiceB = !isChoiceB;
                if (isChoiceB) {
                    examTestItemBIcon.setImageResource(R.drawable.icon_exam_b_selected);
                } else {
                    examTestItemBIcon.setImageResource(R.drawable.icon_exam_b_normal);
                }
                handerAns(2, answerTrue);
            }
        });

        examTestItemC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChoiceC = !isChoiceC;
                if (isChoiceC) {
                    examTestItemCIcon.setImageResource(R.drawable.icon_exam_c_selected);
                } else {
                    examTestItemCIcon.setImageResource(R.drawable.icon_exam_c_normal);
                }
                handerAns(3, answerTrue);
            }
        });

        examTestItemD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChoiceD = !isChoiceD;
                if (isChoiceD) {
                    examTestItemDIcon.setImageResource(R.drawable.icon_exam_d_selected);
                } else {
                    examTestItemDIcon.setImageResource(R.drawable.icon_exam_d_normal);
                }
                handerAns(4, answerTrue);
            }
        });
    }


    private void handerAns(int ans, String answerTrue) {
        if (type == 3) {

        } else {
            switch (ans) {
                case 1:
                case 2:
                case 3:
                case 4:
                    examTestItemA.setClickable(false);
                    examTestItemB.setClickable(false);
                    examTestItemC.setClickable(false);
                    examTestItemD.setClickable(false);
                    handlerPanDuanTi(ans, answerTrue);
                    break;
            }
        }
    }

    /**
     * 处理判断题
     */
    private void handlerPanDuanTi(int ans, String answerTrue) {
        if ((ans + "").equals(answerTrue)) {
            //正确
            if (getActivity() instanceof AnalogyTestActivity) {
                ((AnalogyTestActivity) getActivity()).next();
            }
            if (getActivity() instanceof ExamTestActivity) {
                ((ExamTestActivity) getActivity()).next();
            }
        } else {
            //错误
            examTestAnswer.setVisibility(View.VISIBLE);
        }

        DBUtils.saveOrUpdateCurrentId(getContext(),
                cursor.getInt(cursor.getColumnIndex("ID")),
                ans+"",
                answerTrue);
    }

    /**
     * 处理选择题
     */
    private void handlerXuanzeTi(int ans, String answerTrue) {
        handlerPanDuanTi(ans, answerTrue);
    }

    /**
     * 处理多选题
     */
    private void handlerDuoXuanTi(int ans, String answerTrue) {

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

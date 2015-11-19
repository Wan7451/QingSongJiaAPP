package com.qingsongjia.qingsongjia.user;

import android.widget.GridView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageTextAdapter;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.wan7451.base.WanActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 改变题库
 */
public class ChangeExamLibsActivity extends WanActivity {


    @Bind(R.id.changeExam_jsz)
    GridView changeExamJsz;  //驾驶证
    @Bind(R.id.changeExam_zgz)
    GridView changeExamZgz;  //资格证

    @Override
    public void initView() {
        ButterKnife.bind(this);

        setBackFinish();
        setContentTitle("却换题库");

        ArrayList<ExamImageText> data = new ArrayList<>();
        data.add(new ExamImageText(R.drawable.icon_exam_bmxz, "报名须知"));
        data.add(new ExamImageText(R.drawable.icon_exam_jtfg, "交通法规"));
        data.add(new ExamImageText(R.drawable.icon_exam_jtbz, "交通标志"));
        data.add(new ExamImageText(R.drawable.icon_exam_xssl, "新手上路"));
        ExamImageTextAdapter adapter = new ExamImageTextAdapter(getContext(), data, R.layout.item_exam_imagetext);
        changeExamJsz.setAdapter(adapter);

        ArrayList<ExamImageText> data2 = new ArrayList<>();
        data2.add(new ExamImageText(R.drawable.icon_exam_bmxz, "报名须知"));
        data2.add(new ExamImageText(R.drawable.icon_exam_jtfg, "交通法规"));
        data2.add(new ExamImageText(R.drawable.icon_exam_jtbz, "交通标志"));
        data2.add(new ExamImageText(R.drawable.icon_exam_xssl, "新手上路"));
        data2.add(new ExamImageText(R.drawable.icon_exam_xssl, "新手上路"));
        ExamImageTextAdapter adapter2 = new ExamImageTextAdapter(getContext(), data2, R.layout.item_exam_imagetext);
        changeExamZgz.setAdapter(adapter2);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_change_exam_libs;
    }

}

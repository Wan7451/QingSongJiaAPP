package com.qingsongjia.qingsongjia.user;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageAdapter;
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
        data.add(new ExamImageText(R.drawable.icon_menu_xc, ""));
        data.add(new ExamImageText(R.drawable.icon_menu_hc, ""));
        data.add(new ExamImageText(R.drawable.icon_menu_kc, ""));
        data.add(new ExamImageText(R.drawable.icon_menu_mt, ""));
        final ExamImageAdapter adapter = new ExamImageAdapter(getContext(), data, R.layout.item_exam_image);
        changeExamJsz.setAdapter(adapter);
        changeExamJsz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setChoicePosition(i);
                adapter.notifyDataSetChanged();
            }
        });

        ArrayList<ExamImageText> data2 = new ArrayList<>();
        data2.add(new ExamImageText(R.drawable.icon_menu_jly, ""));
        data2.add(new ExamImageText(R.drawable.icon_menu_hy, ""));
        data2.add(new ExamImageText(R.drawable.icon_menu_wxp, ""));
        data2.add(new ExamImageText(R.drawable.icon_menu_ky, ""));
        data2.add(new ExamImageText(R.drawable.icon_menu_czc, ""));
        final ExamImageAdapter adapter2 = new ExamImageAdapter(getContext(), data2, R.layout.item_exam_image);
        changeExamZgz.setAdapter(adapter2);
        changeExamZgz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter2.setChoicePosition(i);
                adapter2.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_change_exam_libs;
    }

}

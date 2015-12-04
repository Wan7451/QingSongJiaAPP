package com.qingsongjia.qingsongjia.teacher;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YXConfirmActivity extends WanActivity {


    @Bind(R.id.teacher_icon)
    SimpleDraweeView teacherIcon;
    @Bind(R.id.teacher_name)
    TextView teacherName;
    @Bind(R.id.teacher_yuyue)
    TextView teacherYuyue;
    @Bind(R.id.teacher_kemu)
    TextView teacherKemu;
    @Bind(R.id.teacher_chepai)
    TextView teacherChepai;
    @Bind(R.id.teacher_time)
    TextView teacherTime;
    @Bind(R.id.teacher_neirong)
    TextView teacherNeirong;
    @Bind(R.id.yxconfirm_quexi)
    TextView yxconfirmQuexi;
    @Bind(R.id.yxconfirm_queren)
    TextView yxconfirmQueren;
    private int id;

    @Override
    public void initView() {
        setBackFinish();
        setContentTitle("训练确认");
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", 0);

        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startYXEvaluate(getContext(), id);
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_yxconfirm;
    }

}

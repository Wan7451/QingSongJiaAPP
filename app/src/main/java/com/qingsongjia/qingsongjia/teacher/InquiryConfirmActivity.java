package com.qingsongjia.qingsongjia.teacher;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyPeiLian;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InquiryConfirmActivity extends WanActivity {


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
    @Bind(R.id.yxconfirm_queren)
    Button yxconfirmQueren;
    private MyPeiLian peiLian;

    @Override
    public void initView() {
        setContentTitle("预约");
        setBackFinish();
        peiLian = getIntent().getParcelableExtra("data");
        ButterKnife.bind(this);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_inquiry_confirm;
    }

}

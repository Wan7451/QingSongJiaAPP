package com.qingsongjia.qingsongjia.user;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyTeacherActivity extends WanActivity {


    @Bind(R.id.teacher_icon)
    SimpleDraweeView teacherIcon;
    @Bind(R.id.teacher_name)
    TextView teacherName;
    @Bind(R.id.teacher_sex)
    TextView teacherSex;
    @Bind(R.id.teacher_phone)
    TextView teacherPhone;
    @Bind(R.id.teacher_school)
    TextView teacherSchool;
    @Bind(R.id.teacher_numb)
    TextView teacherNumb;
    @Bind(R.id.teacher_model)
    TextView teacherModel;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("教练信息");
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_teacher;
    }

}

package com.qingsongjia.qingsongjia.yuexun;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 约训详情
 */
public class S_TrainingDetailActivity extends WanActivity {


    @Bind(R.id.pingjia)
    Button pingjia;
    @Bind(R.id.student_icon)
    SimpleDraweeView studentIcon;
    @Bind(R.id.student_name)
    TextView studentName;
    @Bind(R.id.student_yuyue)
    TextView studentYuyue;
    @Bind(R.id.student_kemu)
    TextView studentKemu;
    @Bind(R.id.student_chepai)
    TextView studentChepai;
    @Bind(R.id.student_time)
    TextView studentTime;
    @Bind(R.id.student_neirong)
    TextView studentNeirong;
    @Bind(R.id.student_tel)
    TextView studentTel;
    @Bind(R.id.student_tel_view)
    LinearLayout studentTelView;
    private MyYueKao data;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("练习详情");

        data = getIntent().getParcelableExtra("data");
        studentName.setText(data.getDri_coach_nm());

        studentTime.setText(data.getDri_dt_str());
        studentYuyue.setText(data.getDri_start_hm() + "时 -" + data.getDri_end_hm() + "时");
        studentKemu.setText(data.getDri_sub_nm_nm());
        studentNeirong.setText(data.getDri_learning_content());

        studentChepai.setText(data.getDri_plate_num());

        if (!TextUtils.isEmpty(data.getDri_coach_file())) {
            studentIcon.setImageURI(Uri.parse(data.getDri_coach_file()));
        }

        studentTel.setText(data.getDri_coach_tel());
        studentTelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.CALL",
                        Uri.parse("tel:"+data.getDri_coach_tel()));
                startActivity(intent);
            }
        });

        pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //学生评论练习
                UIManager.startStudentYXEval(getContext(), data);
            }
        });
    }


    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_test_detail;
    }

}

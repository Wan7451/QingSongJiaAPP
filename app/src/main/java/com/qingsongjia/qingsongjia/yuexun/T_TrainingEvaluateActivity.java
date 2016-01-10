package com.qingsongjia.qingsongjia.yuexun;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class T_TrainingEvaluateActivity extends WanActivity {


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
    @Bind(R.id.teacher_tel)
    TextView teacherTel;
    @Bind(R.id.teacher_mark)
    EditText teacherMark;
    @Bind(R.id.yxconfirm_queren)
    Button yxconfirmQueren;


    int id;
    @Bind(R.id.teacher_tel_view)
    LinearLayout teacherTelView;
    private MyYueKao yueKao;

    @Override
    public void initView() {
        setContentTitle("训练评论");
        setBackFinish();
        ButterKnife.bind(this);

        yueKao = getIntent().getParcelableExtra("data");
        id = yueKao.getId();

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        teacherName.setText(yueKao.getDri_student_nm());
        teacherKemu.setText(yueKao.getDri_sub_nm_nm());
        teacherTime.setText(sdformat.format(new Date(yueKao.getDri_dt().getTime())));
        teacherYuyue.setText(yueKao.getDri_start_hm() + "-" + yueKao.getDri_end_hm());
        teacherNeirong.setText(yueKao.getDri_learning_content());

        teacherChepai.setText(yueKao.getDri_campus_nm());

        if(!TextUtils.isEmpty(yueKao.getDri_student_file())){
            teacherIcon.setImageURI(Uri.parse(yueKao.getDri_student_file()));
        }
        teacherTel.setText(yueKao.getDri_student_tel());
        teacherTelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.CALL",
                        Uri.parse("tel:"+yueKao.getDri_student_tel()));
                startActivity(intent);
            }
        });

        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mark = teacherMark.getText().toString();
                if (TextUtils.isEmpty(mark)) {
                    showToast("评价内容不能为空");
                    return;
                }
                NetRequest.markTeacher(getContext(),view, id + "", mark, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("评价成功");
                        finish();
                        colseActivity(T_TrainingConfirmActivity.class);
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("评价失败");
                        } else {
                            showToast(error);
                        }
                    }
                });
            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_yxevaluate;
    }

}

package com.qingsongjia.qingsongjia.yuexun;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class T_TrainingConfirmActivity extends WanActivity {


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
    @Bind(R.id.teacher_tel)
    TextView teacherTel;
    @Bind(R.id.teacher_tel_view)
    LinearLayout teacherTelView;
    private int id;
    private MyYueKao yueKao;

    @Override
    public void initView() {
        setBackFinish();
        setContentTitle("训练确认");
        ButterKnife.bind(this);


        yueKao = getIntent().getParcelableExtra("data");
        id = yueKao.getId();
        teacherName.setText(yueKao.getDri_student_nm());
        teacherKemu.setText(yueKao.getDri_sub_nm_nm());

        teacherChepai.setText(yueKao.getDri_campus_nm());
        teacherTime.setText(yueKao.getDri_plate_num());


        teacherYuyue.setText(yueKao.getDri_start_hm() + "时 -" + yueKao.getDri_end_hm() + "时");
        teacherNeirong.setText(yueKao.getDri_learning_content());

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

        //确认
        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetRequest.studentCome(getContext(),view, id + "", "2", new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("确认成功");
                        UIManager.startYXEvaluate(getContext(), yueKao);
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("确认失败");
                        } else {
                            showToast(error);
                        }
                    }
                });

            }
        });
        //缺席
        yxconfirmQuexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetRequest.studentCome(getContext(),view, id + "", "1", new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("确认成功");
                        UIManager.startYXEvaluate(getContext(), yueKao);

                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("确认失败");
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
        return R.layout.activity_yxconfirm;
    }

}

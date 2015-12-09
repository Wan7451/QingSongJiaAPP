package com.qingsongjia.qingsongjia.teacher;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private MyYueKao yueKao;

    @Override
    public void initView() {
        setBackFinish();
        setContentTitle("训练确认");
        ButterKnife.bind(this);


        yueKao = getIntent().getParcelableExtra("data");
        id=yueKao.getId();
        SimpleDateFormat  sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        teacherName.setText(yueKao.getDri_student_nm());
        teacherKemu.setText(yueKao.getDri_sub_nm_nm());
        teacherTime.setText(sdformat.format(new Date(yueKao.getDri_dt().getTime())));
        teacherYuyue.setText(yueKao.getDri_start_hm()+"-"+yueKao.getDri_end_hm());
        teacherNeirong.setText(yueKao.getDri_learning_content());

        //确认
        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetRequest.studentCome(getContext(), id + "", "2", new NetUtils.NetUtilsHandler() {
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
                NetRequest.studentCome(getContext(), id + "", "1", new NetUtils.NetUtilsHandler() {
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

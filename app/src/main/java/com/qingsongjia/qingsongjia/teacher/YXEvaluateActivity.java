package com.qingsongjia.qingsongjia.teacher;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YXEvaluateActivity extends WanActivity {


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

    @Override
    public void initView() {
        setContentTitle("训练确认");
        setBackFinish();

        id = getIntent().getIntExtra("id", 0);
        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mark = teacherMark.getText().toString();
                if (TextUtils.isEmpty(mark)) {
                    showToast("评价内容不能为空");
                    return;
                }
                NetRequest.markStudent(getContext(),id+"",mark, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("确认成功");
                        finish();
                        colseActivity(YXConfirmActivity.class);
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
        return R.layout.activity_yxevaluate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

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
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 学员练习评价
 */
public class S_TrainingEvaluateActivity extends WanActivity {


    @Bind(R.id.pingjia)
    Button pingjia;
    @Bind(R.id.content)
    EditText content;
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
        setContentTitle("约训评论");

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
                String mark = content.getText().toString();
                if (TextUtils.isEmpty(mark)) {
                    showToast("评价内容不能为空");
                    return;
                }
                NetRequest.markStudent(getContext(),view, data.getId() + "", mark, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("评价成功");
                        EventBus.getDefault().post(new EventData(EventData.TYPE_REFRESH_LIANXI,null));
                        finish();
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
        return R.layout.activity_yxevaluate2;
    }

}

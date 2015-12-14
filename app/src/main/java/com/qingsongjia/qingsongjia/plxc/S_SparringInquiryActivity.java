package com.qingsongjia.qingsongjia.plxc;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class S_SparringInquiryActivity extends WanActivity {


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


   private PeiLian peiLian;//陪练数据

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("预约");

        setRightText("投诉", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startTouSuo(getContext(), peiLian.getId());
            }
        });

        peiLian = getIntent().getParcelableExtra("peilian");
        teacherName.setText(peiLian.getContactName());
        teacherTime.setText(peiLian.getMeetingDate_str()+" "+peiLian.getMeetingTime()+"时");

        if(!TextUtils.isEmpty(peiLian.getDri_file_path())){
            teacherIcon.setImageURI(Uri.parse(peiLian.getDri_file_path()));
        }

        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //预约陪练
                NetRequest.inquiryPeiLian(getContext(),peiLian.getId(), new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("预约成功");
                        finish();
                        UIManager.startPeiLianPingJia(getContext(),peiLian);

                    }

                    @Override
                    public void onResponseError(String error) {
                        if(TextUtils.isEmpty(error)){
                            showToast("预约失败");
                        }else {
                            showToast(error);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_send_inquiry;
    }

}

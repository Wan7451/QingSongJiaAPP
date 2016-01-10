package com.qingsongjia.qingsongjia.plxc;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyPeiLian;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 陪练评价
 */

public class T_SparringEvaluateActivity extends WanActivity {

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
    @Bind(R.id.teacher_tele)
    TextView teacherTele;
    @Bind(R.id.teacher_type)
    TextView teacherType;
    @Bind(R.id.teacher_price)
    TextView teacherPrice;
    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.yxconfirm_queren)
    Button yxconfirmQueren;

    private PeiLian peiLian;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        setContentTitle("评价");
        setBackFinish();
        peiLian = getIntent().getParcelableExtra("data");


        if (!TextUtils.isEmpty(peiLian.getDri_file_path())) {
            teacherIcon.setImageURI(Uri.parse(peiLian.getDri_file_path()));
        }
        teacherName.setText(peiLian.getDri_user_nm());
        teacherTime.setText(peiLian.getMeetingDate_str());
        teacherNeirong.setText(peiLian.getDri_partner_type_nm());
        teacherTele.setText(peiLian.getTelephoneNumber());

        teacherType.setText(peiLian.getDri_partner_type_nm());
        teacherPrice.setText(peiLian.getDri_price() + "元");


        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String remark = content.getText().toString();
                if (TextUtils.isEmpty(remark)) {
                    showToast("评论不能为空");
                    return;
                }

                NetRequest.peijiapinglui(getContext(),view, peiLian.getId(), remark, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("评论成功");
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if(TextUtils.isEmpty(error)){
                            showToast("评论失败");
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
        return R.layout.activity_pei_lian_ping_jia;
    }

}

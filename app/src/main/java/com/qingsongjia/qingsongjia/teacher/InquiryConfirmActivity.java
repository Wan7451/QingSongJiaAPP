package com.qingsongjia.qingsongjia.teacher;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyPeiLian;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 陪练确认接口
 */
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
    @Bind(R.id.teacher_tele)
    TextView teacherTele;
    @Bind(R.id.teacher_type)
    TextView teacherType;
    @Bind(R.id.teacher_price)
    TextView teacherPrice;
    @Bind(R.id.teacher_typeText)
    TextView teacherTypeText;
    private MyPeiLian peiLian;

    @Override
    public void initView() {
        setContentTitle("预约");
        setBackFinish();
        ButterKnife.bind(this);
        setRightText("投诉", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startTouSuo(getContext(), peiLian.getId());
            }
        });

        peiLian = getIntent().getParcelableExtra("data");



        if (!TextUtils.isEmpty(peiLian.getDri_file_path())) {
            teacherIcon.setImageURI(Uri.parse(peiLian.getDri_file_path()));
        }
        teacherName.setText(peiLian.getDri_user_nm());
        teacherTime.setText(peiLian.getMeetingDate_str());
        teacherNeirong.setText(peiLian.getDri_partner_type_nm());
        teacherTele.setText(peiLian.getTelephoneNumber());

        teacherType.setText(peiLian.getDri_partner_type_nm());
        teacherPrice.setText(peiLian.getDri_price()+"元");
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_inquiry_confirm;
    }

}

package com.qingsongjia.qingsongjia.plxc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 陪练已经评价
 */
public class T_SparringShowEvalActivity extends WanActivity {


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
    @Bind(R.id.teacher_typeText)
    TextView teacherTypeText;
    @Bind(R.id.content)
    TextView content;

    private PeiLian peiLian;

    @Override
    public void initView() {
        setContentTitle("已评价");
        setBackFinish();
        ButterKnife.bind(this);

        setRightText("投诉", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startTouSuo(getContext(), peiLian.getId());
            }
        });

        peiLian = getIntent().getParcelableExtra("data");

        if (!TextUtils.isEmpty(peiLian.getDri_consult_file_path())) {
            teacherIcon.setImageURI(Uri.parse(peiLian.getDri_consult_file_path()));
        }
        teacherName.setText(peiLian.getDri_coach_nm());
        teacherYuyue.setText("");
        teacherKemu.setText("预约时间");
        teacherTime.setText(peiLian.getMeetingDate_str() + " " + peiLian.getMeetingTime() + "点");

        teacherNeirong.setText(peiLian.getDri_partner_type_nm());

        teacherTele.setText(peiLian.getDri_coach_tel());
        teacherTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(peiLian.getDri_user_tel())){
                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setTitle("提示");
                    builder.setMessage("是否要拨打教练的电话");
                    builder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent phoneIntent = new Intent("android.intent.action.CALL",
                                    Uri.parse("tel:" + peiLian.getDri_coach_tel()));
                            //启动
                            startActivity(phoneIntent);
                        }
                    });
                    builder.setNegativeButton("不了",null);
                    builder.show();
                }
            }
        });
        teacherType.setText(peiLian.getDri_partner_type_nm());
        teacherPrice.setText(peiLian.getDri_price() + "元");
        teacherTypeText.setText(peiLian.getDri_comments());
        content.setText("学员评价:\n"+peiLian.getDri_remark());

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_t_sparring_show_eval;
    }

}

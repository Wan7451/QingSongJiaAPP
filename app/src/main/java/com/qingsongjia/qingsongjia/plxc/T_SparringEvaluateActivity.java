package com.qingsongjia.qingsongjia.plxc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

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

        setRightText("投诉", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startTouSuo(getContext(), peiLian.getId());
            }
        });

        if (!TextUtils.isEmpty(peiLian.getDri_consult_file_path())) {
            teacherIcon.setImageURI(Uri.parse(peiLian.getDri_consult_file_path()));
        }
        teacherName.setText(peiLian.getDri_coach_nm());
        teacherYuyue.setText("");
        teacherKemu.setText("预约时间");
        teacherTime.setText(peiLian.getMeetingDate_str()
                +" "+peiLian.getMeetingTime()+"点");


        teacherNeirong.setText("");

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
                        EventBus.getDefault().post(new EventData(EventData.TYPE_REFRESH_PEIJIA,null));
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

package com.qingsongjia.qingsongjia.plxc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

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
    @Bind(R.id.teacher_type)
    TextView teacherType;
    @Bind(R.id.teacher_price)
    TextView teacherPrice;
    @Bind(R.id.teacher_typeText)
    TextView teacherTypeText;
    @Bind(R.id.teacher_tele)
    TextView teacherTele;


    private PeiLian peiLian;//陪练数据

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("抢单");


        peiLian = getIntent().getParcelableExtra("peilian");
        teacherName.setText(peiLian.getDri_user_nm());
        teacherKemu.setText("预约时间");
        teacherTime.setText(peiLian.getMeetingDate_str() + " " + peiLian.getMeetingTime() + "时");
        teacherYuyue.setText("");

        teacherType.setText(peiLian.getDri_partner_type_nm());
        teacherPrice.setText(peiLian.getDri_price() + "元");
        teacherTypeText.setText(peiLian.getDri_comments());

        if (!TextUtils.isEmpty(peiLian.getDri_file_path())) {
            teacherIcon.setImageURI(Uri.parse(peiLian.getDri_file_path()));
        }

        teacherTele.setText(peiLian.getDri_user_tel());
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
                                    Uri.parse("tel:" + peiLian.getDri_user_tel()));
                            //启动
                            startActivity(phoneIntent);
                        }
                    });
                    builder.setNegativeButton("不了",null);
                    builder.show();
                }
            }
        });

        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                User user = LocalPreference.getCurrentUser(getContext());
                if (TextUtils.isEmpty(user.getDri_type())) {
                    UIManager.startLogin(getContext());
                    return;
                }

                int coachId = LocalPreference.getCurrentUserData(getContext()).getDri_coach_id();
                if (coachId == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("提示");
                    builder.setMessage("未分配教练，不能进行陪练，请联系负责人员");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                    return;
                }

                //预约陪练
                NetRequest.inquiryPeiLian(getContext(), null, peiLian.getId(), new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("预约成功,请到 我的陪练行程查看详情");
                        EventBus.getDefault().post(new EventData(EventData.TYPE_REFRESH_PEIJIA, null));
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("预约失败");
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
        return R.layout.activity_send_inquiry;
    }

}

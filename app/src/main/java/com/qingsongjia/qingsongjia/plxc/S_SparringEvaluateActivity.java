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
import android.widget.LinearLayout;
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

public class S_SparringEvaluateActivity extends WanActivity {

    @Bind(R.id.teacher_icon)
    SimpleDraweeView teacherIcon;
    @Bind(R.id.teacher_name)
    TextView teacherName;
    @Bind(R.id.teacher_yuyue)
    TextView teacherYuyue;
    @Bind(R.id.teacher_kemu)
    TextView teacherKemu;
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

    @Bind(R.id.yxconfirm_queren)
    Button yxconfirmQueren;

    @Bind(R.id.teacher_typeText)
    TextView teacherTypeText;
    @Bind(R.id.evaluate_content)
    EditText evaluateContent;
    @Bind(R.id.evaluate_view)
    LinearLayout evaluateView;
    @Bind(R.id.evaluate_t_view)
    LinearLayout evaluateTView;

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

        if (!TextUtils.isEmpty(peiLian.getDri_file_path())) {
            teacherIcon.setImageURI(Uri.parse(peiLian.getDri_file_path()));
        }
        teacherName.setText(peiLian.getDri_user_nm());
        teacherYuyue.setText("");
        teacherKemu.setText("预约时间");
        teacherTime.setText(peiLian.getMeetingDate_str()
                + " " + peiLian.getMeetingTime() + "点");


        teacherNeirong.setText("");
        teacherTele.setText(peiLian.getDri_user_tel());
        teacherTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(peiLian.getDri_user_tel())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                    builder.setNegativeButton("不了", null);
                    builder.show();
                }
            }
        });

        teacherType.setText(peiLian.getDri_partner_type_nm());
        teacherPrice.setText(peiLian.getDri_price() + "元");

        teacherTypeText.setText(peiLian.getDri_comments());

        switch (peiLian.getStatus()) {
            case 2:
                setContentTitle("等待教练确认");
                yxconfirmQueren.setVisibility(View.GONE);
                evaluateView.setVisibility(View.GONE);
                break;
            case 3:
                if (peiLian.getDri_remark_state() == 2) {
                    setContentTitle("教练评价");
                    yxconfirmQueren.setVisibility(View.GONE);

                    if (TextUtils.isEmpty(peiLian.getDri_remark_two())) {
                        evaluateContent.setText("教练暂时没有评论");
                    } else {
                        evaluateContent.setText(peiLian.getDri_remark_two());
                    }
                    evaluateContent.setFocusable(false);
                } else {
                    //隐藏教练评论
                    evaluateTView.setVisibility(View.GONE);
                }
                break;
        }


        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String remark = evaluateContent.getText().toString();
                if (TextUtils.isEmpty(remark)) {
                    showToast("评论不能为空");
                    return;
                }

                NetRequest.peijiapinglui2JiaoLian(getContext(), view, peiLian.getId(), remark, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("评论成功");
                        EventBus.getDefault().post(new EventData(EventData.TYPE_REFRESH_PEIJIA, null));
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("评论失败");
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
        return
                R.layout.activity_s_sparring_evaluate;
    }

}

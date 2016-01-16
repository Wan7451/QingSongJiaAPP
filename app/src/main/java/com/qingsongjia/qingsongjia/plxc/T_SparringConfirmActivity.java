package com.qingsongjia.qingsongjia.plxc;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
 * 陪练确认接口
 */
public class T_SparringConfirmActivity extends WanActivity {


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

    private PeiLian peiLian;

    @Override
    public void initView() {
        setContentTitle("确认");
        setBackFinish();
        ButterKnife.bind(this);

        peiLian = getIntent().getParcelableExtra("data");

        if (!TextUtils.isEmpty(peiLian.getDri_file_path())) {
            teacherIcon.setImageURI(Uri.parse(peiLian.getDri_file_path()));
        }
        teacherName.setText(peiLian.getDri_coach_nm());
        teacherYuyue.setText("");
        teacherKemu.setText("预约时间");
        teacherTime.setText(peiLian.getMeetingDate_str()
                +" "+peiLian.getMeetingTime()+"点");

        teacherTypeText.setText(peiLian.getDri_comments());
        teacherNeirong.setText("");
        teacherTele.setText(peiLian.getTelephoneNumber());

        teacherType.setText(peiLian.getDri_partner_type_nm());
        teacherPrice.setText(peiLian.getDri_price()+"元");


        yxconfirmQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetRequest.queRenPeiLian(getContext(),view,peiLian.getId(), new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("确认成功");
                        EventBus.getDefault().post(new EventData(EventData.TYPE_REFRESH_PEIJIA,null));
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if(TextUtils.isEmpty(error)){
                            showToast("确认失败");
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
        return R.layout.activity_inquiry_confirm;
    }

}

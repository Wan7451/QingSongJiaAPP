package com.qingsongjia.qingsongjia.driverexam;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.TeacherDetail;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class TeacherDetailActivity extends WanActivity {

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
    @Bind(R.id.teacher_max)
    TextView teacherMax;
    @Bind(R.id.teacher_curr)
    TextView teacherCurr;
    private TeacherDetail teacherDetail;

    @Override
    public void initView() {
        setBackFinish();
        setContentTitle("详细信息");
        String id = getIntent().getStringExtra("id");
        ButterKnife.bind(this);

        NetRequest.loadCoursePlanByCoursePlanId(getContext(), id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                String detail = response.getString(0);

                SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");

                teacherDetail = JSONObject.parseObject(detail, TeacherDetail.class);
                teacherName.setText(teacherDetail.getDri_coach_nm());
                teacherKemu.setText(teacherDetail.getDri_sub_nm_nm());
                teacherChepai.setText(teacherDetail.getDri_plate_num());
                teacherNeirong.setText(teacherDetail.getDri_report());
                teacherTime.setText(format.format(new Date(teacherDetail.getDri_date().getTime())));
                teacherYuyue.setText(teacherDetail.getDri_start_hm()+"-"+teacherDetail.getDri_end_hm());
                teacherMax.setText(teacherDetail.getDri_rv_num()+"人");
                teacherCurr.setText(teacherDetail.getDri_rvd_num()+"人");

                if(TextUtils.isEmpty(teacherDetail.getDri_file_path())){
                    teacherIcon.setImageURI(Uri.parse("res:// /"+R.drawable.default_head));
                }else {
                    teacherIcon.setImageURI(Uri.parse(teacherDetail.getDri_file_path()));
                }
            }

            @Override
            public void onResponseError(String error) {

            }
        });


        findViewById(R.id.teacher_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(
                        new EventData(EventData.TYPE_YUELIAN,teacherDetail));

                getAppContext().closeActivity(TeacherListActivity.class);
                getAppContext().closeActivity(TeacherDetailActivity.class);

            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_teacher_detail;
    }

}

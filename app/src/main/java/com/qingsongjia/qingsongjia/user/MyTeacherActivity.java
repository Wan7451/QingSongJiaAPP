package com.qingsongjia.qingsongjia.user;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyTeacher;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 教练信息
 */
public class MyTeacherActivity extends WanActivity {


    @Bind(R.id.teacher_icon)
    SimpleDraweeView teacherIcon;
    @Bind(R.id.teacher_name)
    TextView teacherName;
    @Bind(R.id.teacher_sex)
    TextView teacherSex;
    @Bind(R.id.teacher_phone)
    TextView teacherPhone;
    @Bind(R.id.teacher_school)
    TextView teacherSchool;
    @Bind(R.id.teacher_numb)
    TextView teacherNumb;
    @Bind(R.id.teacher_model)
    TextView teacherModel;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("教练信息");

        loadData();
    }

    private void loadData() {
        NetRequest.loadMyTeacher(getContext(), null,new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                String data = response.getString(0);
                MyTeacher teacher = JSONObject.parseObject(data, MyTeacher.class);

                String icon = teacher.getDri_file_path();
                if (!TextUtils.isEmpty(icon)) {
                    teacherIcon.setImageURI(Uri.parse(icon));
                }
                teacherName.setText(teacher.getDri_nm());
                teacherPhone.setText(teacher.getDri_mobile());
                teacherSchool.setText(teacher.getDri_campus_nm());
                teacherNumb.setText(teacher.getDri_plate_num());
                teacherModel.setText(teacher.getDri_car_type());
            }

            @Override
            public void onResponseError(String error) {

            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_teacher;
    }

}

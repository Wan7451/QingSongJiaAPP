package com.qingsongjia.qingsongjia.user;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.map.Text;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.UserData;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;
import com.wan7451.choicepicture.ChoicePicturesActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StudentMessageActivity extends WanActivity {


    @Bind(R.id.icon)
    SimpleDraweeView icon;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.numb)
    EditText numb;
    @Bind(R.id.chool)
    TextView chool;
    @Bind(R.id.teacher)
    TextView teacher;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("个人信息");
        loadData();

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChoicePicturesActivity.startChoicePicturesForResult(getContext(),111);
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_student_message;
    }

    private void fillData(final UserData loginData) {
        if (!TextUtils.isEmpty(loginData.getDri_nm())) {
            name.setText(loginData.getDri_nm());
        } else {
            name.setText("用户" + loginData.getId());
        }

        if (!TextUtils.isEmpty(loginData.getDri_campus_nm())) {
            chool.setText(loginData.getDri_campus_nm());
            chool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIManager.startSchoolDetail(getContext(), loginData.getDri_campus_id());
                }
            });
        }else {
            chool.setText("还没有报名驾校");
        }
        String teacherName = loginData.getDri_coach_nm();
        if (!TextUtils.isEmpty(teacherName)) {
            teacher.setText(teacherName);
            teacher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    UIManager.startMyTeacher(getContext());
                }
            });
        }else {
            teacher.setText("还没有分配教练");
        }
        String iconPath = loginData.getDri_file_path();
        if (TextUtils.isEmpty(iconPath)) {
            icon.setImageURI(Uri.parse("http://7xlt5l.com1.z0.glb.clouddn.com/1449734769519ohgmmj3048628"));
        } else {
            icon.setImageURI(Uri.parse(iconPath));
        }
    }


    private void loadData() {


        UserData loginData = LocalPreference.getCurrentUserData(getContext());

        fillData(loginData);


//        NetRequest.loadMyData(getContext(), new NetUtils.NetUtilsHandler() {
//            @Override
//            public void onResponseOK(JSONArray response, int total) {
//                String data = response.getString(0);
//
//                LocalPreference.saveCurrentUserData(getContext(), data);
//
//                UserData loginData = JSONObject.parseObject(data, UserData.class);
//
//                fillData(loginData);
//            }
//
//            @Override
//            public void onResponseError(String error) {
//
//            }
//        });
    }


}

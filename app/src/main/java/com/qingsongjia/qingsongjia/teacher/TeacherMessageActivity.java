package com.qingsongjia.qingsongjia.teacher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.UserData;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.others.ChoicePictureDialog;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class TeacherMessageActivity extends WanActivity {


    @Bind(R.id.icon)
    SimpleDraweeView icon;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.numb)
    TextView numb;
    @Bind(R.id.chool)
    TextView chool;

    private String dri_file_path;//头像路径
    private Integer dri_campus_id;//驾校编码
    protected Integer id;//（人员ID）
    private String dri_nm;//姓名
    private ChoicePictureDialog dialog;


    @Override
    public void initView() {
        setContentTitle("个人信息");
        setRightText("保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nm= name.getText().toString();
                if(TextUtils.isEmpty(nm)){
                    showToast("姓名不能为空");
                    return;
                }
                dri_nm=nm;

                String path= (String) icon.getTag();
                if(!TextUtils.isEmpty(path)){
                    dri_file_path=path;
                }
                saveTearcherMessage(view);
            }
        });
        setBackFinish();
        ButterKnife.bind(this);
        fillData();

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ChoicePictureDialog(getContext(), dri_file_path, icon);
            }
        });
    }


    private void fillData() {

        final UserData loginData = LocalPreference.getCurrentUserData(getContext());


        dri_file_path=loginData.getDri_file_path();
        dri_campus_id=loginData.getDri_campus_id();
        id=loginData.getDid();
        dri_nm=loginData.getDri_nm();


        if(!TextUtils.isEmpty(loginData.getDri_file_path())){
            icon.setImageURI(Uri.parse(loginData.getDri_file_path()));
        }
        if (!TextUtils.isEmpty(loginData.getDri_nm())) {
            name.setText(loginData.getDri_nm());
        } else {
            name.setText("教练" + loginData.getDid());
        }
        numb.setText(loginData.getDri_tel());


        if (!TextUtils.isEmpty(loginData.getDri_campus_nm())) {
            chool.setText(loginData.getDri_campus_nm());
            chool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIManager.startSchoolDetail(getContext(), loginData.getDri_campus_id(),-1);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog.onActivityResult(requestCode, resultCode, data);
    }

    private void saveTearcherMessage(View v) {
        NetRequest.saveTeacher(getContext(), v,dri_nm, dri_file_path, dri_campus_id, id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                showToast("保存成功");
                EventBus.getDefault().post(new EventData(EventData.TYPE_REFRESH_MENU, null));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },300);
            }

            @Override
            public void onResponseError(String error) {
                if(TextUtils.isEmpty(error)){
                    showToast("保存失败");
                }else {
                    showToast(error);
                }
            }
        });
    }


    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_teacher_nessage;
    }

}

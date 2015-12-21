package com.qingsongjia.qingsongjia.user;

import android.content.Intent;
import android.net.Uri;
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

public class StudentMessageActivity extends WanActivity {


    @Bind(R.id.icon)
    SimpleDraweeView icon;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.numb)
    TextView numb;
    @Bind(R.id.chool)
    TextView chool;
    @Bind(R.id.teacher)
    TextView teacher;
    private ChoicePictureDialog dialog;


    private String dri_file_path;//头像路径
    private String id;//人员ID
    private Integer dri_campus_id;//驾校编码
    private Integer dri_coach_id;//教练编码
    private String dri_coach_nm;//教练名称
    protected Integer update_id;//（修正人）
    private String dri_nm;//姓名

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("个人信息");
        loadData();

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ChoicePictureDialog(getContext(),dri_file_path, icon);
            }
        });


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
                saveStudetnMessage();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_student_message;
    }

    private void fillData(final UserData loginData) {


        dri_file_path = loginData.getDri_file_path();
        id = loginData.getDid() + "";
        dri_campus_id = loginData.getDri_campus_id();
        dri_coach_id = loginData.getDri_coach_id();
        dri_coach_nm = loginData.getDri_coach_nm();
        dri_nm = loginData.getDri_nm();


        numb.setText(loginData.getDri_tel());

        if (!TextUtils.isEmpty(loginData.getDri_nm())) {
            name.setText(loginData.getDri_nm());
        } else {
            name.setText("用户" + loginData.getDid());
        }

        if (!TextUtils.isEmpty(loginData.getDri_campus_nm())) {
            chool.setText(loginData.getDri_campus_nm());
            chool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIManager.startSchoolDetail(getContext(), loginData.getDri_campus_id());
                }
            });
        } else {
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
        } else {
            teacher.setText("还没有分配教练");
        }
        String iconPath = loginData.getDri_file_path();
        if (TextUtils.isEmpty(iconPath)) {
            icon.setImageURI(Uri.parse("http://7xlt5l.com1.z0.glb.clouddn.com/1449734769519ohgmmj3048628"));
        } else {
            icon.setImageURI(Uri.parse(iconPath));
        }
    }


    //保存用户数据
    private void saveStudetnMessage() {

        NetRequest.saveStudent(getContext(),
                dri_file_path,
                id,
                dri_campus_id,
                dri_coach_id,
                dri_coach_nm,
                dri_nm,
                new NetUtils.NetUtilsHandler() {
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

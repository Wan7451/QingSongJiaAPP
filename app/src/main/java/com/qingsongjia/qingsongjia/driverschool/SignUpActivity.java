package com.qingsongjia.qingsongjia.driverschool;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends WanActivity {

    @Bind(R.id.signup_type)
    RadioGroup signupType;
    @Bind(R.id.signup_careType)
    TextView signupCareType;
    @Bind(R.id.signup_careTypeView)
    LinearLayout signupCareTypeView;
    @Bind(R.id.signup_name)
    EditText signupName;
    @Bind(R.id.signup_phone)
    EditText signupPhone;
    @Bind(R.id.signup_mark)
    EditText signupMark;
    @Bind(R.id.signup_signUp)
    Button signupSignUp;

    private String[] items = {"小车(C1/C2/C3)", "货车(A2/B2)", "客车(A1/A3/B1)", "摩托(D/E/F)"};

    private int dri_goal = 2;//学习目的 数据字典 1.咨询 2.报名
    private int id;
    private int dri_entry_fee;
    private int dri_car_type=1;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setContentTitle("报名信息");
        setBackFinish();

        id = getIntent().getIntExtra("id", 0);
        dri_entry_fee = getIntent().getIntExtra("entry_fee", 0);

        if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
            UIManager.startLogin(getContext());
            finish();
            return;
        }

        signupCareTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("选择车型");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        signupCareType.setText(items[i]);
                        dri_car_type=i+1;
                    }
                });
                builder.show();
            }
        });

        signupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.signup_ask:
                        //咨询
                        dri_goal = 1;
                        break;
                    case R.id.signup_up:
                        //报名
                        dri_goal = 2;
                        break;
                }
            }
        });

        signupSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                id//主键--报名学员id
//                dri_goal//学习目的 数据字典 1.咨询 2.报名
//                dri_car_type //车型
//                dri_nm //姓名
//                dri_remark //备注
//                dri_entry_fee  // 报名费

              String name=  signupName.getText().toString();
              String phone=  signupPhone.getText().toString();
              String dri_remark=  signupMark.getText().toString();

                if(TextUtils.isEmpty(name)){
                    showToast("姓名不能为空");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    showToast("电话不能为空");
                    return;
                }
             NetRequest.signUp(getContext(),
                     view,
                     dri_goal,
                     dri_car_type,
                     name,
                     phone,
                     dri_remark,
                     dri_entry_fee,
                     new NetUtils.NetUtilsHandler() {
                         @Override
                         public void onResponseOK(JSONArray response, int total) {
                                showToast("报名成功");
                             finish();
                         }

                         @Override
                         public void onResponseError(String error) {
                                if(TextUtils.isEmpty(error)){
                                    showToast("报名失败");
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
        return R.layout.activity_sign_up;
    }

}

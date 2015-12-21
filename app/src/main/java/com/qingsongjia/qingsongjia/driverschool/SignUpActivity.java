package com.qingsongjia.qingsongjia.driverschool;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
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

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setContentTitle("报名信息");
        setBackFinish();

        int id = getIntent().getIntExtra("id", 0);

        if(TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())){
            UIManager.startLogin(getContext());
            finish();
            return;
        }

        signupCareTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] data = {"C1(手动挡)", "C2(自动挡)"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("选择车型");
                builder.setItems(data, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        signupCareType.setText(data[i]);
                    }
                });
                builder.show();
            }
        });

        signupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });
        ((RadioButton)signupType.getChildAt(0)).setChecked(true);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_sign_up;
    }

}

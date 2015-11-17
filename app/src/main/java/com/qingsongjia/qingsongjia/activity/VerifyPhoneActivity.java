package com.qingsongjia.qingsongjia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VerifyPhoneActivity extends WanActivity {


    @Bind(R.id.verify_phone)
    EditText verifyPhone;
    @Bind(R.id.verify_pasd)
    EditText verifyPasd;
    @Bind(R.id.verify_verify)
    Button verifyVerify;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setLeftIcon(R.drawable.icon_exit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setContentTitle("验证手机");
        verifyVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startSetPassword(getContext());
            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_verify_phone;
    }

}

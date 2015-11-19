package com.qingsongjia.qingsongjia.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends WanActivity {


    @Bind(R.id.register_phone)
    EditText registerPhone;
    @Bind(R.id.register_pasd)
    EditText registerPasd;
    @Bind(R.id.register_code)
    EditText registerCode;
    @Bind(R.id.register_protocol)
    TextView registerProtocol;
    @Bind(R.id.register_register)
    Button registerRegister;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setLeftIcon(R.drawable.icon_exit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setContentTitle("注册");
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_register;
    }

}

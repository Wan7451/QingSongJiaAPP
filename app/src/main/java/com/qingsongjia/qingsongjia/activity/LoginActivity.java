package com.qingsongjia.qingsongjia.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends WanActivity {


    @Bind(R.id.login_phone)
    EditText loginPhone;
    @Bind(R.id.login_pasd)
    EditText loginPasd;
    @Bind(R.id.login_canNotLogin)
    TextView loginCanNotLogin;
    @Bind(R.id.login_fastRegist)
    TextView loginFastRegist;
    @Bind(R.id.login_login)
    Button loginLogin;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setLeftIcon(R.drawable.icon_exit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setContentTitle("登陆");

        loginFastRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startRegister(getContext());
            }
        });

        loginCanNotLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startVerify(getContext());
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_login;
    }

}

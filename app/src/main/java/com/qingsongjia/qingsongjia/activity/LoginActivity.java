package com.qingsongjia.qingsongjia.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
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


        User user = LocalPreference.getCurrentUser(getContext());
        if(!TextUtils.isEmpty(user.getDri_type())){
            startActivity(new Intent(getContext(),MainActivity.class));
            finish();
        }
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

        loginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tel = loginPhone.getText().toString();
                String pasd = loginPasd.getText().toString();
                if (TextUtils.isEmpty(tel)) {
                    showToast("账户不能为空！");
                    return;
                }

                if (TextUtils.isEmpty(pasd)) {
                    showToast("密码不能为空！");
                    return;
                }


                NetRequest.loginLogin(getContext(), tel, pasd, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        String user = response.getString(0);
                        LocalPreference.saveCurrentUser(getContext(), user);
                        startActivity(new Intent(getContext(), MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (!TextUtils.isEmpty(error)) {
                            showToast(error);
                        } else {
                            showToast("登陆失败，请重试");
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_login;
    }

}

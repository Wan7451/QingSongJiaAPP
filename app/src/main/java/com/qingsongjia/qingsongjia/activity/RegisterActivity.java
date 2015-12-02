package com.qingsongjia.qingsongjia.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
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


        registerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tel = registerPhone.getText().toString();
                String pasd = registerPasd.getText().toString();
                String code = registerCode.getText().toString();

                if (TextUtils.isEmpty(tel)) {
                    showToast("手机号码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(pasd)) {
                    showToast("密码不能为空！");
                    return;
                }

                NetRequest.register(getContext(), tel, pasd, code, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("注册成功，请登陆");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 300);
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (!TextUtils.isEmpty(error)) {
                            showToast(error);
                        } else {
                            showToast("注册失败，请重试");
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_register;
    }

}

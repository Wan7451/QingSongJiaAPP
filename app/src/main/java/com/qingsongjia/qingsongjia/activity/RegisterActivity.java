package com.qingsongjia.qingsongjia.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends WanActivity {


    @Bind(R.id.verify_phone)
    EditText verifyPhone;
    @Bind(R.id.verify_code)
    TextView verifyCode;
    @Bind(R.id.verify_pasd)
    EditText verifyPasd;
    @Bind(R.id.register_pasd)
    EditText registerPasd;
    @Bind(R.id.register_code)
    EditText registerCode;
    @Bind(R.id.register_protocol)
    TextView registerProtocol;
    @Bind(R.id.register_register)
    Button registerRegister;


    private Timer timer;
    private static int TIME = 60;
    private String vcode;

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

                String tel = verifyPhone.getText().toString();
                String pasd = registerPasd.getText().toString();
                String code = registerCode.getText().toString();
               String vpasd= verifyPasd.getText().toString();

                if (TextUtils.isEmpty(tel)) {
                    showToast("手机号码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(pasd)) {
                    showToast("密码不能为空！");
                    return;
                }



                if(TextUtils.isEmpty(vpasd)){
                    showToast("验证码不能为空！");
                    return;
                }

                if(!TextUtils.equals(vpasd,vcode)){
                    showToast("验证码输入错误！");
                    return;
                }

                if(pasd.length()<6){
                    showToast("密码最短6位！");
                    return;
                }

                if(pasd.length()>16){
                    showToast("密码最长16位！");
                    return;
                }

                NetRequest.register(getContext(),view, tel, pasd, code, new NetUtils.NetUtilsHandler() {
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


        verifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String phone = verifyPhone.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    showToast("手机号码不能为空！");
                    return;
                }

                NetRequest.getVerification(getContext(),view, phone, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, final int total) {
                        showToast("验证码已发送");
                        if (timer != null) {
                            timer.cancel();
                            timer = null;
                        }
                        timer = new Timer();
                        TIME = 60;
                        verifyCode.setClickable(false);
                        verifyCode.setTextColor(getResources().getColor(R.color.text_default_hint));
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        verifyCode.setText(TIME + "秒");
                                        TIME--;
                                        if (TIME == 0) {
                                            verifyCode.setTextColor(getResources().getColor(R.color.text_default));
                                            verifyCode.setText("获取验证码");
                                            verifyCode.setClickable(true);
                                            timer.cancel();
                                        }
                                    }
                                });
                            }
                        }, 1000, 1000);

//                        private Integer id;//用户id
//                        private String dri_unm;//账户名（手机号）
//                        private String dri_verification_code;//验证码

                        JSONObject object = response.getJSONObject(0);
                        vcode = object.getString("checkNum");
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (!TextUtils.isEmpty(error)) {
                            showToast(error);
                        } else {
                            showToast("获取验证码失败，请重试");
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

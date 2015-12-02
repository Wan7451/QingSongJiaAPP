package com.qingsongjia.qingsongjia.activity;

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
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VerifyPhoneActivity extends WanActivity {


    @Bind(R.id.verify_phone)
    EditText verifyPhone;
    @Bind(R.id.verify_pasd)
    EditText verifyPasd;
    @Bind(R.id.verify_code)
    TextView verifyCode;
    @Bind(R.id.verify_verify)
    Button verifyVerify;


    private Timer timer;
    private static int TIME = 60;
    private String vcode;
    private String phone;

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


        verifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                phone = verifyPhone.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    showToast("手机号码不能为空！");
                    return;
                }

                NetRequest.getVerificationForPwd(getContext(), phone, new NetUtils.NetUtilsHandler() {
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
                        vcode = object.getString("dri_verification_code");
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

        verifyVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(vcode)) {
                    showToast("请先获取验证码");
                    return;
                }
                String vpasd = verifyPasd.getText().toString();
                if (TextUtils.isEmpty(vpasd)) {
                    showToast("请输入短信验证码");
                    return;
                }

//                NetRequest.doVaildVerificationForPwd(getContext(),vcode,vpasd,new NetRequest());

                if (TextUtils.equals(vcode, vpasd)) {
                    UIManager.startSetPassword(getContext());
                    finish();
                } else {
                    showToast("输入的验证码有误，请重试");
                }


            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_verify_phone;
    }

}

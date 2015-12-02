package com.qingsongjia.qingsongjia.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SetPasswordActivity extends WanActivity {


    @Bind(R.id.setPsd_pasd)
    EditText setPsdPasd;
    @Bind(R.id.setPsd_set)
    Button setPsdSet;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setLeftIcon(R.drawable.icon_exit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setContentTitle("设置密码");

        setPsdSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pasd = setPsdPasd.getText().toString();

                if (TextUtils.isEmpty(pasd)) {
                    showToast("新密码不能为空");
                    return;
                }
                NetRequest.changePasd(getContext(), pasd, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("密码修改成功，请重新登陆！");
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
                            showToast("修改密码失败，请重试");
                        }
                    }
                });
            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_set_password;
    }

}

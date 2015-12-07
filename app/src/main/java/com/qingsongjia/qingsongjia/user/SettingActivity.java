package com.qingsongjia.qingsongjia.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.LoginActivity;
import com.qingsongjia.qingsongjia.activity.MainActivity;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends WanActivity {


    @Bind(R.id.login_out)
    Button loginOut;

    @Override
    public void initView() {
        setContentTitle("设置");
        setBackFinish();
        ButterKnife.bind(this);
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalPreference.clearData(getContext());
                startActivity(new Intent(getContext(), LoginActivity.class));
                finish();


            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_setting;
    }

}

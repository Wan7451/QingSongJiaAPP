package com.qingsongjia.qingsongjia.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qingsongjia.qingsongjia.R;
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

            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_set_password;
    }

}

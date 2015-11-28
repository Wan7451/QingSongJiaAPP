package com.qingsongjia.qingsongjia.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TiXianActivity extends WanActivity {


    @Bind(R.id.tixian_card)
    EditText tixianCard;
    @Bind(R.id.tixian_charge)
    EditText tixianCharge;
    @Bind(R.id.tixian_subject)
    Button tixianSubject;

    @Override
    public void initView() {
        setBackFinish();
        setContentTitle("余额提现");
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.qingsongjia.qingsongjia.user;

import android.os.Bundle;
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
        ButterKnife.bind(this);
        setContentTitle("余额提现");


        tixianSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NetRequest.tixian(getContext(), new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {

                    }

                    @Override
                    public void onResponseError(String error) {

                    }
                });
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_ti_xian;
    }

}

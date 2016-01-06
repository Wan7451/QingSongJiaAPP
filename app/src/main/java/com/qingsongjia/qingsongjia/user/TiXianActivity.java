package com.qingsongjia.qingsongjia.user;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * 余额提现
 */
public class TiXianActivity extends WanActivity {


    @Bind(R.id.tixian_card)
    EditText tixianCard;
    @Bind(R.id.tixian_charge)
    EditText tixianCharge;
    @Bind(R.id.tixian_name)
    EditText tixianName;
    @Bind(R.id.tixian_subject)
    Button tixianSubject;


    @Override
    public void initView() {
        setBackFinish();
        ButterKnife.bind(this);
        setContentTitle("余额提现");

        final int money = getIntent().getIntExtra("money", 0);


        tixianCharge.setHint("不能超过"+money+"元");

        tixianSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String card = tixianCard.getText().toString();
                String charge = tixianCharge.getText().toString();
                String name = tixianName.getText().toString();

                if (TextUtils.isEmpty(card)) {
                    showToast("卡号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(charge)) {
                    showToast("金额不能为空");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    showToast("姓名不能为空");
                    return;
                }

                int c = Integer.parseInt(charge);

                if(c>money){
                    showToast("不能超过"+money+"元");
                    return;
                }

                c = 0 - c;

                NetRequest.tixian(getContext(), card, name, c + "", new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("提现申请成功");
                        finish();
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

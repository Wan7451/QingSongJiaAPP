package com.qingsongjia.qingsongjia.user;

import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyMessageActivity extends WanActivity {


    @Bind(R.id.myMessage_yqm)
    TextView myMessageYqm;
    @Bind(R.id.myMessage_yhj_Left)
    TextView myMessageYhjLeft;
    @Bind(R.id.myMessage_yhj_Right)
    TextView myMessageYhjRight;
    @Bind(R.id.myMessage_jf_Left)
    TextView myMessageJfLeft;
    @Bind(R.id.myMessage_jf_Right)
    TextView myMessageJfRight;
    @Bind(R.id.myMessage_yue_Left)
    TextView myMessageYueLeft;
    @Bind(R.id.myMessage_yue_Right)
    TextView myMessageYueRight;
    @Bind(R.id.myMessage_yhj)
    LinearLayout myMessageYhj;
    @Bind(R.id.myMessage_jf)
    LinearLayout myMessageJf;
    @Bind(R.id.myMessage_yue)
    LinearLayout myMessageYue;


    private int integral; //分
    private int money;   //元
    private int coupons; //张


    @Override
    public void initView() {
        setBackFinish();
        setContentTitle("我的信息");
        ButterKnife.bind(this);

        myMessageJf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startMyJiFei(getContext(),integral);
            }
        });
        myMessageYue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startMyYuE(getContext(),money);
            }
        });

        loadData();

    }

    private void loadData() {
        NetRequest.getMyMessage(getContext(), new NetUtils.NetUtilsHandler() {



            @Override
            public void onResponseOK(JSONArray response, int total) {
                String data = response.getString(0);
                JSONObject object = JSONObject.parseObject(data);
                String code = object.getString("dri_invitation_code");
                myMessageYqm.setText(code);
                coupons = object.getInteger("coupons");
                myMessageYhjRight.setText(coupons + "张");

                money = object.getInteger("money");
                myMessageYueRight.setText(money + "元");

                integral = object.getInteger("integral");
                myMessageJfRight.setText(integral + "分");
            }

            @Override
            public void onResponseError(String error) {

            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_message;
    }

}

package com.qingsongjia.qingsongjia.plxc;

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
 * 陪练投诉
 */
public class T_SparringComplaintActivity extends WanActivity {


    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.tousu)
    Button tousu;
    private int id;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("投诉");
        id = getIntent().getIntExtra("id", 0);

        tousu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String con = content.getText().toString();
                if (TextUtils.isEmpty(con)) {
                    showToast("投诉内容不能为空");
                    return;
                }
                NetRequest.peilianTouSu(getContext(),view, id, con, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("投诉成功");
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("投诉失败");
                        } else {
                            showToast(error);
                        }
                    }
                });
            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_tou_su;
    }

}

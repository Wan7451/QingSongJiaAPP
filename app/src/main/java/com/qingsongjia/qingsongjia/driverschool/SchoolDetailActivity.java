package com.qingsongjia.qingsongjia.driverschool;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

public class SchoolDetailActivity extends WanActivity {


    private int id;

    @Override
    public void initView() {
        setContentTitle("驾校详情");
        setBackFinish();
        id = getIntent().getIntExtra("id", 0);

        loadData();
    }

    private void loadData() {
        NetRequest.loadSchoolDeatail(getContext(),id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {

            }

            @Override
            public void onResponseError(String error) {

            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_school_info;
    }
}

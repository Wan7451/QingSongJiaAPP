package com.qingsongjia.qingsongjia.plxc;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyPeiLian;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 陪练列表
 */
public class T_SparringListActivity extends WanListActivity {


    private ArrayList<MyPeiLian> data=new ArrayList<>();

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("陪练行程");
//        setRightText("编辑");
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        MyTestAdapter adapter=new MyTestAdapter(getContext(),data,R.layout.item_mytest_list);
//        View header= getLayoutInflater().inflate(R.layout.activity_my_test,null);
//        adapter.addHeaderView(header);
        return adapter;
    }

    @Override
    protected void loadData() {

        NetRequest.loadMyPeiLian(getContext(),null, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(),MyPeiLian.class));
                }

                loadFinish("暂时没有学员陪练");

            }

            @Override
            public void onResponseError(String error) {
                loadError();
            }
        });


    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startInquiryConfirm(getContext(),data.get(posotion));
    }

    static class MyTestAdapter extends WanAdapter<MyPeiLian>{

        public MyTestAdapter(Context context, List<MyPeiLian> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, MyPeiLian item) {
            holder.setText(R.id.time,item.getMeetingDate_str()+" "+item.getMeetingTime()+"时");
//            holder.setText(R.id.kemu,"");
//            holder.setText(R.id.zhishidian,"");
            holder.setText(R.id.status,item.getDri_remark_state_nm());

        }
    }
}
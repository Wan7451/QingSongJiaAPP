package com.qingsongjia.qingsongjia.teacher;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyStudent;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyStudentActivity extends WanListActivity {

    private ArrayList<MyStudent> data=new ArrayList<>();

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        MyTestAdapter adapter=new MyTestAdapter(getContext(),data,R.layout.item_myt_student);
        View header= getLayoutInflater().inflate(R.layout.activity_my_test,null);
        adapter.addHeaderView(header);
        return adapter;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("我的学员");
        setRightText("编辑");
    }

    @Override
    protected void loadData() {

        NetRequest.loadMyStudent(getContext(), new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if(response.size()>0){
                    data.addAll(JSONArray.parseArray(response.toJSONString(),MyStudent.class));
                }
                loadFinish("");
            }

            @Override
            public void onResponseError(String error) {
                loadError();
            }
        });
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }


    static class MyTestAdapter extends WanAdapter<MyStudent>{

        public MyTestAdapter(Context context, List<MyStudent> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, MyStudent item) {
            holder.setText(R.id.mystudent_name,item.getDri_nm());
            holder.setText(R.id.mystudent_kemu,item.getDri_course_pro_nm());
            holder.setText(R.id.mystudent_phone,item.getDri_tel());
        }
    }
}

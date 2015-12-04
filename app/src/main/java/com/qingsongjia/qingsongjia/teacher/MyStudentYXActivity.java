package com.qingsongjia.qingsongjia.teacher;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyStudentYXActivity extends WanListActivity {

    private ArrayList<String> data=new ArrayList<>();

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        MyTestAdapter adapter=new MyTestAdapter(getContext(),data,R.layout.item_mytest_list);
        View header= getLayoutInflater().inflate(R.layout.activity_my_test,null);
        adapter.addHeaderView(header);
        return adapter;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("学员约训");
        setRightText("编辑");
    }

    @Override
    protected void loadData() {
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        loadFinish("");
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startYXConfirm(getContext(),posotion);
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }


    static class MyTestAdapter extends WanAdapter<String>{

        public MyTestAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, String item) {

        }
    }

}

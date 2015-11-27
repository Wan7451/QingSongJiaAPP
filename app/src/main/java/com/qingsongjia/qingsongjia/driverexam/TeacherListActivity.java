package com.qingsongjia.qingsongjia.driverexam;

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

public class TeacherListActivity extends WanListActivity {

    ArrayList<String> data=new ArrayList<>();

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();
        setContentTitle("教练列表");
        setBackFinish();
    }

    @Override
    public WanAdapter getAdapter() {
        return new TeacherAdapter(getContext(),data,R.layout.item_teacher_list);
    }

    @Override
    protected void loadData() {
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
        UIManager.startTeacherDetail(getContext());
    }

    static class TeacherAdapter extends WanAdapter<String>{

        public TeacherAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, String item) {

        }
    }

}

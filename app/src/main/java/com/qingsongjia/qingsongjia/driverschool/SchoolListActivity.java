package com.qingsongjia.qingsongjia.driverschool;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.SchoolListAdapter;
import com.qingsongjia.qingsongjia.bean.School;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

/**
 * Created by wanggang on 15/11/17.
 */
public class SchoolListActivity extends WanListActivity {

    ArrayList<School> data=new ArrayList<>();


    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("驾校列表");
    }

    @Override
    protected boolean isShowTitleView() {
        return true;
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        return new SchoolListAdapter(getContext(),data, R.layout.item_school_list);
    }

    @Override
    protected void loadData() {
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        data.add(new School("A"));
        loadFinish("数据空");
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}

package com.qingsongjia.qingsongjia.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.SchoolListAdapter;
import com.qingsongjia.qingsongjia.bean.School;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.advancedview.FilterMenuView;
import com.wan7451.base.WanListFragment;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

public class SchoolFragment extends WanListFragment {
    ArrayList<School> data=new ArrayList<>();


    @Override
    protected boolean addData() {
        return true;
    }

    @Override
    protected boolean isShowTitleView() {
        return false;
    }

    @Override
    protected boolean loadData() {


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

        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        return new SchoolListAdapter(getContext(),data, R.layout.item_school_list);
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        Toast.makeText(getContext(),data.get(posotion).getName(),Toast.LENGTH_SHORT).show();
        UIManager.startSchoolList(getContext());
    }

    @Override
    public View getListHeaderView() {
        Button btn=new Button(getContext());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> data=new ArrayList<String>();
                data.add("A");
                data.add("A");
                data.add("A");
                data.add("A");
                data.add("A");

                FilterMenuView.showFilterMenu(getActivity(), data, view, new WanAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(int posotion, WanViewHolder holder) {

                    }
                });
            }
        });

        return btn;
    }

    //R.layout.fragment_school

}

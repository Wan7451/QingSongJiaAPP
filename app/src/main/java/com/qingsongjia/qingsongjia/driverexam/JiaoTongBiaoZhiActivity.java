package com.qingsongjia.qingsongjia.driverexam;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 交通标志
 */
public class JiaoTongBiaoZhiActivity extends WanListActivity {


    private ArrayList<String> data=new ArrayList<>();

    @Override
    public void initView() {
        super.initView();
        setContentTitle("交通标志");
        setBackFinish();
        setIsShowLocalData();
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {

        JiaoTongFaGuiAdapter adapter=new JiaoTongFaGuiAdapter(getContext(),data, R.layout.item_jtfg_list);
        return adapter ;
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
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startJiaoTongBiaoZhiGrid(getContext());
    }


    static class JiaoTongFaGuiAdapter extends WanAdapter<String>{

        private List<String> data=new ArrayList<>();

        public JiaoTongFaGuiAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
            data.add("");
            data.add("");
            data.add("");
            data.add("");
            data.add("");
        }

        @Override
        public void convert(WanViewHolder holder, int position, String item) {
           RecyclerView icons= holder.getView(R.id.jtfg_icons);
            JTFGIconsAdapter adapter=new JTFGIconsAdapter(getContext(),data,R.layout.item_jtfg_bz);
            icons.setAdapter(adapter);
            LinearLayoutManager manager=new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            icons.setLayoutManager(manager);
            icons.setClickable(false);
        }
    }

    static class JTFGIconsAdapter extends WanAdapter<String>{

        public JTFGIconsAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, String item) {

        }
    }
}

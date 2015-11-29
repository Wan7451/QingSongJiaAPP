package com.qingsongjia.qingsongjia.driverexam;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;
import com.wan7451.wanadapter.list.CommonAdapter;
import com.wan7451.wanadapter.list.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JiaoTongBiaoZhiGridActivity extends WanActivity {


    @Bind(R.id.mainList)
    GridView mainList;
    private List<String> data=new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("标志");

        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        BiaoZhiAdapte adapte=new BiaoZhiAdapte(getContext(),data,R.layout.item_jtbz_grid);
        mainList.setAdapter(adapte);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_jiao_tong_biao_zhi_grid;
    }


    class BiaoZhiAdapte extends CommonAdapter<String>{


        public BiaoZhiAdapte(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, int position, String item) {

        }
    }

}

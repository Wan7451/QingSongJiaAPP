package com.qingsongjia.qingsongjia.plxc;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.PeiLian;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 陪练列表
 */
public class T_SparringListActivity extends WanListActivity {


    private ArrayList<PeiLian> data=new ArrayList<>();

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
                    data.addAll(JSONArray.parseArray(response.toJSONString(),PeiLian.class));
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
        PeiLian item=data.get(posotion);

        switch (item.getStatus()){
            case 1:
                //未抢单
                UIManager.startPeiLianNormal(getContext(),data.get(posotion));
                break;
            case 2:
                //已抢单,确认
                UIManager.startInquiryConfirm(getContext(),data.get(posotion));
                break;
            case 3:
                if(item.getDri_remark_state_two()==2){
                    //已评价
                    UIManager.startPeiLianEvaluate(getContext(),data.get(posotion));
                }else {
                    //已确认，评价
                    UIManager.startPeiLianPingJia(getContext(), data.get(posotion));
                }
                break;
            case 4:
                //已取消，
                showToast("已经取消抢单");
                break;
        }

    }

    static class MyTestAdapter extends WanAdapter<PeiLian>{

        public MyTestAdapter(Context context, List<PeiLian> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, PeiLian item) {
            holder.setText(R.id.time,item.getMeetingDate_str()+" "+item.getMeetingTime()+"时");

            holder.setText(R.id.status,item.getDri_remark_state_nm());

            holder.getView(R.id.kemu).setVisibility(View.GONE);

            TextView zhishidian=  holder.getView(R.id.zhishidian);
            TextView status=  holder.getView(R.id.status);
            zhishidian.setText(item.getDri_comments());

            //1 未抢单 2已抢单 3已确认 4已取消
            switch (item.getStatus()){
                case 1:
                    status.setText("未抢单");
                    break;
                case 2:
                    status.setText("已抢单");
                    break;
                case 3:
                    if(item.getDri_remark_state_two()==2){
                        //已评价
                        status.setText("已评价");
                    }else {
                        //已确认，评价
                        status.setText("已确认");
                    }
                    break;
                case 4:
                    status.setText("已取消");
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    public void onEventMainThread(EventData data) {
        if (data.getType() == EventData.TYPE_REFRESH_PEIJIA) {
            refreshing();
        }
    }
}
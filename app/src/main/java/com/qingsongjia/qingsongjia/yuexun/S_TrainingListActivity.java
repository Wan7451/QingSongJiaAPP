package com.qingsongjia.qingsongjia.yuexun;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 学员  我的练习 约训列表
 */
public class S_TrainingListActivity extends WanListActivity {


    private ArrayList<MyYueKao> data = new ArrayList<>();

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("我的练习");
        setRightText("编辑");
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        MyTestAdapter adapter = new MyTestAdapter(getContext(), data, R.layout.item_mytest_list);
        View header = getLayoutInflater().inflate(R.layout.activity_my_test, null);
        adapter.addHeaderView(header);
        return adapter;
    }

    @Override
    protected void loadData() {

        //我的约练
        NetRequest.getMyYueLian(getContext(), new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), MyYueKao.class));
                }
                loadFinish("");
            }

            @Override
            public void onResponseError(String error) {

            }
        });


    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startMyTestDetail(getContext(),data.get(posotion));
    }

    static class MyTestAdapter extends WanAdapter<MyYueKao> {

        public MyTestAdapter(Context context, List<MyYueKao> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, MyYueKao item) {
            TextView time = holder.getView(R.id.time);
            String t = item.getDri_dt_str()+ " " + item.getDri_start_hm() + "-" + item.getDri_end_hm();
            time.setText(t);

            TextView keme = holder.getView(R.id.kemu);
            keme.setText(item.getDri_sub_nm());

            TextView status = holder.findViewById(R.id.status);
            String state = item.getDri_state_nm();
            status.setText(state);

        }
    }
}

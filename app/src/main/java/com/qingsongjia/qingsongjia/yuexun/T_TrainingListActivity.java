package com.qingsongjia.qingsongjia.yuexun;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 教练 学生约训
 */

public class T_TrainingListActivity extends WanListActivity {

    private ArrayList<MyYueKao> data = new ArrayList<>();


    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("学员约训");
//        setRightText("编辑");
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
        MyTestAdapter adapter = new MyTestAdapter(getContext(), data, R.layout.item_mytest_list);
        return adapter;
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void loadData() {

        //我的约练
        NetRequest.getTeacherYueLian(getContext(), new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), MyYueKao.class));
                }
                loadFinish("暂时没有学员预约练习~");
            }

            @Override
            public void onResponseError(String error) {
                loadError();
            }
        });


    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
//        MyYueKao yueKao = data.get(posotion);
//        if (yueKao.getDri_state().equals("1")) {
//            UIManager.startYueXunConfirm(getContext(), data.get(posotion));
//        }else if(yueKao.getDri_remark_state().equals("1")){
//            UIManager.startYueXunComnent(getContext(),data.get(posotion));
//        }

        if (posotion == 0) {
            UIManager.startYueXunConfirm(getContext(), data.get(posotion));
        } else {
            UIManager.startYueXunComnent(getContext(), data.get(posotion));
        }
    }

    static class MyTestAdapter extends WanAdapter<MyYueKao> {

        private final SimpleDateFormat sdformat;

        public MyTestAdapter(Context context, List<MyYueKao> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
            //24小时制
            sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }

        @Override
        public void convert(WanViewHolder holder, int position, MyYueKao item) {
            if (item == null)
                return;

            TextView time = holder.getView(R.id.time);
            time.setText(item.getDri_dt_str() + " " + item.getDri_start_hm() + "时 -" + item.getDri_end_hm() + "时");

            TextView keme = holder.getView(R.id.kemu);
            keme.setText(item.getDri_sub_nm());

            TextView status = holder.findViewById(R.id.status);

//            if (item.getDri_state().equals("1")) {//未学习
//
//            }else

            TextView name = holder.getView(R.id.name);
            name.setVisibility(View.VISIBLE);
            name.setText(item.getDri_student_nm());

            if (TextUtils.equals(item.getDri_remark_state(), "2")) {
                String state = item.getDri_remark_state_nm(); //评价状态
                status.setText(state);
            } else {
                status.setText("待评价");
                status.setTextColor(getContext().getResources().getColor(R.color.text_import));
            }

            if (TextUtils.equals(item.getDri_state(), "2")
                    && TextUtils.equals(item.getDri_stu_remark_state(), "2")) {
                status.setText("已完成");
                status.setTextColor(getContext().getResources().getColor(R.color.text_default_hint));
            }

            holder.setText(R.id.kemu, item.getDri_sub_nm_nm());
            holder.setText(R.id.zhishidian, item.getDri_learning_content());

        }
    }

}

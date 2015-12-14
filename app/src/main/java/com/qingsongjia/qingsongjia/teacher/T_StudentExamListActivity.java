package com.qingsongjia.qingsongjia.teacher;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyKaoShi;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 教练 学生约考
 */
public class T_StudentExamListActivity extends WanListActivity {

    private ArrayList<MyKaoShi> data = new ArrayList<>();


    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("学员约考");
        setRightText("编辑");
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
    protected void loadData() {

        NetRequest.getTeacherYueKao(getContext(), new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        data.clear();
                        if (!TextUtils.equals("[{}]", response.toJSONString())) {
                            List<MyKaoShi> kaoShis = JSONArray.parseArray(response.toJSONString(), MyKaoShi.class);
                            data.addAll(kaoShis);
                        }
                        loadFinish("暂时没有学员");
                    }

                    @Override
                    public void onResponseError(String error) {
                            loadError();

                    }
                }
        );


    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }

    static class MyTestAdapter extends WanAdapter<MyKaoShi> {

        public MyTestAdapter(Context context, List<MyKaoShi> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, MyKaoShi item) {
            TextView time = holder.getView(R.id.time);
            String t = item.getDri_dt_str()+" "+(item.getDri_tm().equals("1")?"上午":"下午");
            time.setText(t);

            TextView keme = holder.getView(R.id.kemu);
            keme.setText(item.getDri_sub_nm_nm());

            TextView zsd = holder.findViewById(R.id.zhishidian);
            zsd.setText(item.getDri_student_nm() + "");

            TextView status = holder.findViewById(R.id.status);
            status.setText(item.getDri_result());

            holder.getView(R.id.arrow).setVisibility(View.GONE);
        }
    }

}

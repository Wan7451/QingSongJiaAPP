package com.qingsongjia.qingsongjia.driverschool;

import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.SchoolEvaluataAdapter;
import com.qingsongjia.qingsongjia.bean.SchoolDetail;
import com.qingsongjia.qingsongjia.bean.SchoolEvaluate;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanListFragment;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 *本校学员点评
 */
public class SchoolEvaluateFragment extends WanListFragment {
    private int id;
    private List<SchoolEvaluate> data = new ArrayList<>();

    @Override
    public void initView(View v) {
        super.initView(v);
        SchoolEvaluateActivity activity = (SchoolEvaluateActivity) getActivity();
        SchoolDetail detail = activity.getDetail();
        id = detail.getDri_campus_id();
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    protected boolean loadData() {

        NetRequest.loadCurrentSchoolEvaluate(getContext(),null, id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals(response.toJSONString(), "[{}]")) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), SchoolEvaluate.class));
                }
                loadFinish("暂时还没有点评");
            }


            @Override
            public void onResponseError(String error) {
                loadError();
            }
        });

        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        return new SchoolEvaluataAdapter(getContext(), data, R.layout.item_school_evaluate);
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }
}

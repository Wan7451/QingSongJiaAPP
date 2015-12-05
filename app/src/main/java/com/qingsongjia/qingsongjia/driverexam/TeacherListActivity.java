package com.qingsongjia.qingsongjia.driverexam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.Teacher;
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

public class TeacherListActivity extends WanListActivity {

    ArrayList<Teacher> data = new ArrayList<>();
    String time;

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
        time = getIntent().getStringExtra("time");
    }

    @Override
    public WanAdapter getAdapter() {
        return new TeacherAdapter(getContext(), data, R.layout.item_teacher_list);
    }

    @Override
    protected void loadData() {


        NetRequest.queryForCoursePlanList(getContext(), time, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {

                data.clear();
                List<Teacher> teachers = JSONArray.parseArray(response.toJSONString(), Teacher.class);
                if (teachers.size() != 0)
                    data.addAll(teachers);
                loadFinish("");
            }

            @Override
            public void onResponseError(String error) {

            }
        });


    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startTeacherDetail(getContext(), data.get(posotion).getId() + "");
    }

    static class TeacherAdapter extends WanAdapter<Teacher> {

        SimpleDateFormat format;

        public TeacherAdapter(Context context, List<Teacher> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
            format = new SimpleDateFormat("yyyy年MM月dd日");
        }

        @Override
        public void convert(WanViewHolder holder, int position, Teacher item) {
            SimpleDraweeView icon = holder.getView(R.id.teacher_icon);
            holder.setText(R.id.teacher_name, item.getDri_coach_nm());//名字
            holder.setText(R.id.teacher_kemu, item.getDri_sub_nm_nm());//科目
            holder.setText(R.id.teacher_chepai, item.getDri_plate_num());//车牌
            holder.setText(R.id.teacher_neirong, item.getDri_report());//内容
            holder.setText(R.id.teacher_time, format.format(new Date(item.getDri_date().getTime())));//时间
            holder.setText(R.id.teacher_yuyue, item.getDri_start_hm() + "-" + item.getDri_end_hm());//预约时间

            if (TextUtils.isEmpty(item.getDri_file_path())) {
                icon.setImageURI(Uri.parse("res:// /" + R.drawable.default_head));
            } else {
                icon.setImageURI(Uri.parse(item.getDri_file_path()));
            }

        }
    }

}

package com.qingsongjia.qingsongjia.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.LoginActivity;
import com.qingsongjia.qingsongjia.adapter.ItemClickDataAdapter;
import com.qingsongjia.qingsongjia.bean.ItemClickData;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class SettingActivity extends WanActivity implements WanAdapter.OnItemClickListener {


    @Bind(R.id.login_out)
    Button loginOut;
    @Bind(R.id.setting_main)
    RecyclerView settingMain;

    ArrayList<ItemClickData> datas;//展示的数据
    private ItemClickDataAdapter adapter;

    @Override
    public void initView() {
        setContentTitle("设置");
        setBackFinish();
        ButterKnife.bind(this);
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventData(EventData.TYPE_REFRESH_MENU, null));
                LocalPreference.clearData(getContext());
                startActivity(new Intent(getContext(), LoginActivity.class));
                finish();
            }
        });

        datas = new ArrayList<>();
//        datas.add(new ItemClickData(R.drawable.icon_menu_exam, "更新Top图片", "", true));
        adapter = new ItemClickDataAdapter(getContext(), datas, R.layout.item_icontext_arrows);

        settingMain.setAdapter(adapter);
        settingMain.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        switch (posotion) {
            case 0:
                NetRequest.downTopPicture(getContext(),holder.getConvertView(), new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        JSONObject object = response.getJSONObject(0);
                        String path = object.getString("dri_file_path");
                        LocalPreference.savaTopImgPath(getContext(),path);
                        showToast("图片更新成功");
                    }

                    @Override
                    public void onResponseError(String error) {

                    }
                });
                break;
        }
    }
}

package com.qingsongjia.qingsongjia.driverschool;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.qingsongjia.qingsongjia.adapter.SchoolListAdapter;
import com.qingsongjia.qingsongjia.bean.CityData;
import com.qingsongjia.qingsongjia.bean.School;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.base.WanListFragment;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

public class SchoolListActivity extends WanListActivity {


    ArrayList<School> data = new ArrayList<>();


    private String cityCode;  //城市编码
    private int money;  //价格
    private int care;   //人气
    private int praise; //口碑

    private boolean isMoney;
    private boolean isCare;
    private boolean isPraise;

    @Override
    protected boolean addData() {
        return true;
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("找驾校");
    }

    @Override
    protected void loadData() {

        NetRequest.getSchoolList(getContext(), cityCode, money, care, praise, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), School.class));
                }
                getMainView().onRefreshComplete();
                getMainAdapter().notifyDataSetChanged();
                if (data.size() == 0) {
                    Toast.makeText(getContext(), "没有符合当前条件的驾校，请更换条件", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onResponseError(String error) {

            }
        });
    }

    @Override
    public WanAdapter getAdapter() {
        return new SchoolListAdapter(getContext(), data, R.layout.item_school_list);
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startSchoolDetail(getContext(), data.get(posotion).getDri_campus_id());
    }


    @Override
    public void onResume() {
        super.onResume();

        App app = (App) getApplication();
        TextView didian = (TextView) findViewById(R.id.didian);
        if (app.getCurrArea() != null) {
//        CityData cityData = LocalPreference.getCurrentCityData(getContext());
//        CityData areaData = LocalPreference.getCurrentAreaData(getContext());
            CityData cityData = app.getCurrCity();
            CityData areaData = app.getCurrArea();
            cityCode = areaData.getCode();
            if (!TextUtils.isEmpty(cityData.getName()) && !TextUtils.isEmpty(areaData.getName())) {
                didian.setText(cityData.getName() + " " + areaData.getName());
            }
            refreshing();

            app.setCurrCity(null);
            app.setCurrArea(null);
        } else if (app.getCurrCity() != null) {

            CityData cityData = app.getCurrCity();
            cityCode = cityData.getCode();
            if (!TextUtils.isEmpty(cityData.getName())) {
                didian.setText(cityData.getName());
            }
            refreshing();

            app.setCurrCity(null);
            app.setCurrArea(null);
        } else {
            cityCode = "";
            didian.setText("地点选择");
        }
    }

    @Override
    public View getListHeaderView() {


        View header = LayoutInflater.from(getContext()).inflate(R.layout.layout_school_header, null);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        header.setLayoutParams(lp);


        TextView didian = (TextView) header.findViewById(R.id.didian);
        TextView koubei = (TextView) header.findViewById(R.id.koubei);
        TextView renqi = (TextView) header.findViewById(R.id.renqi);
        TextView jiage = (TextView) header.findViewById(R.id.jiage);
        final ImageView kbArrow = (ImageView) header.findViewById(R.id.koubei_arrow);
        final ImageView rqArrow = (ImageView) header.findViewById(R.id.renqi_arrow);
        final ImageView jgArrow = (ImageView) header.findViewById(R.id.jiage_arrow);

        kbArrow.setVisibility(View.GONE);
        rqArrow.setVisibility(View.GONE);
        jgArrow.setVisibility(View.GONE);

        koubei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kbArrow.setVisibility(View.VISIBLE);
                if (isPraise) {
                    praise = 2;
                    kbArrow.setImageResource(R.drawable.icon_arrow_gray_up);
                } else {
                    praise = 1;
                    kbArrow.setImageResource(R.drawable.icon_arrow_gray);
                }
                isPraise = !isPraise;
                refreshing();
            }
        });


        renqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rqArrow.setVisibility(View.VISIBLE);
                if (isCare) {
                    care = 2;
                    rqArrow.setImageResource(R.drawable.icon_arrow_gray_up);
                } else {
                    care = 1;
                    rqArrow.setImageResource(R.drawable.icon_arrow_gray);
                }
                isCare = !isCare;
                refreshing();
            }
        });

        jiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jgArrow.setVisibility(View.VISIBLE);
                if (isMoney) {
                    money = 2;
                    jgArrow.setImageResource(R.drawable.icon_arrow_gray_up);
                } else {
                    money = 1;
                    jgArrow.setImageResource(R.drawable.icon_arrow_gray);
                }
                isMoney = !isMoney;
                refreshing();
            }
        });

        didian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startChoiceCity(getContext());
            }
        });

        return header;
    }

    //R.layout.fragment_school

    public void refreshing() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getMainView().setRefreshing();
            }
        }, 300);
    }
}

package com.qingsongjia.qingsongjia.driverschool;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamAdapter;
import com.qingsongjia.qingsongjia.bean.SchoolDetail;
import com.qingsongjia.qingsongjia.bean.SchoolScore;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SchoolEvaluateActivity extends WanActivity {


    @Bind(R.id.schoolinfo_tv_fen)
    TextView schoolinfoTvFen;
    @Bind(R.id.schoolinfo_tv_ren)
    TextView schoolinfoTvRen;
    @Bind(R.id.schoolinfo_time_rating)
    RatingBar schoolinfoTimeRating;
    @Bind(R.id.schoolinfo_time_fen)
    TextView schoolinfoTimeFen;
    @Bind(R.id.schoolinfo_addr_rating)
    RatingBar schoolinfoAddrRating;
    @Bind(R.id.schoolinfo_addr_fen)
    TextView schoolinfoAddrFen;
    @Bind(R.id.schoolinfo_pass_rating)
    RatingBar schoolinfoPassRating;
    @Bind(R.id.schoolinfo_pass_fen)
    TextView schoolinfoPassFen;
    @Bind(R.id.navigation_tab)
    TabLayout navigationTab;
    @Bind(R.id.navigation_view)
    ViewPager navigationView;
    private int id;
    private SchoolDetail detail;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("驾校点评");

        id = getIntent().getIntExtra("id", 0);

        detail = getIntent().getParcelableExtra("data");

        schoolinfoTvFen.setText(detail.getDri_sum() + "分");
        schoolinfoTvRen.setText(detail.getCountpepole() + "人评分");

        schoolinfoTimeRating.setRating(detail.getDri_time());
        schoolinfoTimeFen.setText(detail.getDri_time() + "分");

        schoolinfoAddrRating.setRating(detail.getDri_place());
        schoolinfoAddrFen.setText(detail.getDri_place() + "分");

        schoolinfoPassRating.setRating(detail.getDri_pass());
        schoolinfoPassFen.setText(detail.getDri_pass() + "分");


        SchoolEvaluateAdapter adapter=new SchoolEvaluateAdapter(getSupportFragmentManager());
        navigationTab.setTabsFromPagerAdapter(adapter);
        navigationView.setAdapter(adapter);
        navigationTab.setTabsFromPagerAdapter(adapter);
        navigationTab.setupWithViewPager(navigationView);
        navigationTab.setTabMode(TabLayout.MODE_FIXED);
        navigationTab.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    public SchoolDetail getDetail() {
        return detail;
    }

    @Deprecated
    private void loadData() {
        NetRequest.getSchoolScores(getContext(), id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                String data = response.getString(0);
                SchoolScore score = JSONObject.parseObject(data, SchoolScore.class);

//                schoolinfoTvFen.setText(score.getDri_sum() + "分");
//                schoolinfoTvRen.setText(score.getCountpepole() + "人评分");

                schoolinfoTimeRating.setRating(score.getDri_time());
                schoolinfoTimeFen.setText(score.getDri_time() + "分");

                schoolinfoAddrRating.setRating(score.getDri_place());
                schoolinfoAddrFen.setText(score.getDri_place() + "分");

                schoolinfoPassRating.setRating(score.getDri_pass());
                schoolinfoPassFen.setText(score.getDri_pass() + "分");
            }

            @Override
            public void onResponseError(String error) {

            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_school_evaluate;
    }

}

package com.qingsongjia.qingsongjia.driverschool;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.SchoolDetail;
import com.qingsongjia.qingsongjia.bean.SchoolScore;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

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
    @Bind(R.id.schoolinfo_eval)
    EditText schoolinfoEval;
    @Bind(R.id.schoolinfo_eval_send)
    TextView schoolinfoEvalSend;
    private int id;
    private SchoolDetail detail;

    private int place, time, pass;
    private SchoolEvaluateAdapter adapter;
    private InputMethodManager imm;

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

        imm = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);

        adapter = new SchoolEvaluateAdapter(getSupportFragmentManager());
        navigationTab.setTabsFromPagerAdapter(adapter);
        navigationView.setAdapter(adapter);
        navigationTab.setTabsFromPagerAdapter(adapter);
        navigationTab.setupWithViewPager(navigationView);
        navigationTab.setTabMode(TabLayout.MODE_FIXED);
        navigationTab.setTabGravity(TabLayout.GRAVITY_FILL);

        schoolinfoTimeRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                time = (int) (v * 10 / 10);
                schoolinfoTimeFen.setText(time + "分");
            }
        });

        schoolinfoAddrRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                place = (int) (v * 10 / 10);
                schoolinfoAddrFen.setText(place + "分");
            }
        });

        schoolinfoPassRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                pass = (int) (v * 10 / 10);
                schoolinfoPassFen.setText(pass + "分");
            }
        });
        schoolinfoEvalSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
                    UIManager.startLogin(getContext());
                    return;
                }

                String eval = schoolinfoEval.getText().toString();
                if (TextUtils.isEmpty(eval)) {
                    showToast("评价内容不能为空");
                    return;
                }

                NetRequest.evaluateSchool(getContext(), view, detail.getDri_campus_id(),
                        place, time, pass, eval, new NetUtils.NetUtilsHandler() {
                            @Override
                            public void onResponseOK(JSONArray response, int total) {
                                showToast("评价成功");
                                Fragment fragment = adapter.getEvalFragment(navigationTab
                                        .getSelectedTabPosition());

                                if (fragment instanceof AllSchoolEvaluateFragment) {
                                    ((AllSchoolEvaluateFragment) fragment).refreshing();
                                }

                                if (fragment instanceof SchoolEvaluateFragment) {
                                    ((SchoolEvaluateFragment) fragment).refreshing();
                                }
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                schoolinfoEval.setText("");

                                loadData();

                                EventBus.getDefault().post(new EventData(EventData.TYPE_REFRESH_SCHOOL, null));
                            }

                            @Override
                            public void onResponseError(String error) {

                            }
                        });
            }
        });
    }

    public SchoolDetail getDetail() {
        return detail;
    }

    private void loadData() {
        NetRequest.loadSchoolDeatail(getContext(), null, detail.getDri_campus_id(), new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                if (!TextUtils.equals(response.toJSONString(), "[{}]")) {
                    String data = response.getString(0);
                    SchoolDetail score = JSONObject.parseObject(data, SchoolDetail.class);


                    schoolinfoTvFen.setText(score.getDri_sum() + "分");
                    schoolinfoTvRen.setText(score.getCountpepole() + "人评分");

                    schoolinfoTimeRating.setRating(score.getDri_time());
                    schoolinfoTimeFen.setText(score.getDri_time() + "分");

                    schoolinfoAddrRating.setRating(score.getDri_place());
                    schoolinfoAddrFen.setText(score.getDri_place() + "分");

                    schoolinfoPassRating.setRating(score.getDri_pass());
                    schoolinfoPassFen.setText(score.getDri_pass() + "分");
                }
            }


            @Override
            public void onResponseError(String error) {
                showToast("加载数据失败");
            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_school_evaluate;
    }

}

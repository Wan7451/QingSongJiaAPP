package com.qingsongjia.qingsongjia.driverschool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.MapActivity;
import com.qingsongjia.qingsongjia.bean.SchoolDetail;
import com.qingsongjia.qingsongjia.bean.StreetView;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;
import com.wan7451.wanadapter.list.CommonAdapter;
import com.wan7451.wanadapter.list.ViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SchoolDetailActivity extends WanActivity {


    @Bind(R.id.schoolinfo_iv_logo)
    SimpleDraweeView schoolinfoIvLogo;
    @Bind(R.id.schoolinfo_tv_name)
    TextView schoolinfoTvName;
    @Bind(R.id.schoolinfo_tv_price)
    TextView schoolinfoTvPrice;
    @Bind(R.id.schoolinfo_layout_price)
    RelativeLayout schoolinfoLayoutPrice;
    @Bind(R.id.schoolinfo_tv_tel)
    TextView schoolinfoTvTel;
    @Bind(R.id.schoolinfo_tv_addr)
    TextView schoolinfoTvAddr;
    @Bind(R.id.schoolinfo_tv_bus)
    TextView schoolinfoTvBus;
    @Bind(R.id.schoolinfo_iv_imgsCount)
    TextView schoolinfoIvImgsCount;
    @Bind(R.id.schoolinfo_layout_img)
    RelativeLayout schoolinfoLayoutImg;
    @Bind(R.id.schoolinfo_gv_imgs)
    GridView schoolinfoGvImgs;
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
    @Bind(R.id.schoolinfo_tv_info)
    TextView schoolinfoTvInfo;
    @Bind(R.id.schoolinfo_btn_baoming)
    Button schoolinfoBtnBaoming;

    @Bind(R.id.schoolinfo_score)
    LinearLayout schoolinfoScore;
    private int id;
    private SchoolDetail detail;

    @Override
    public void initView() {
        setContentTitle("驾校详情");
        setBackFinish();
        id = getIntent().getIntExtra("id", 0);
        ButterKnife.bind(this);
        loadData();


        schoolinfoBtnBaoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startSignUp(getContext(),detail.getDri_campus_id());
            }
        });

        schoolinfoTvAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UIManager.startMapView(getContext(), detail.getDri_nm(), detail.getDri_map_address());
            }
        });

        schoolinfoTvBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startBusRoute(getContext(), detail.getDri_way());
            }
        });

        schoolinfoIvImgsCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSchoolImgs();
            }
        });
        schoolinfoGvImgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showSchoolImgs();
            }
        });

        schoolinfoScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startSchoolEvaluate(getContext(),detail);
            }
        });
    }

    private void showSchoolImgs() {
        ArrayList<StreetView> streetViews = detail.getStreet_view();
        String[] paths = new String[streetViews.size()];
        for (int i = 0; i < streetViews.size(); i++) {
            String path = streetViews.get(i).getDri_file_path();
            paths[i] = path;
        }
        UIManager.startSchoolImages(getContext(), paths);
    }

    private void loadData() {
        NetRequest.loadSchoolDeatail(getContext(), id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                if (!TextUtils.equals(response.toJSONString(), "[{}]")) {
                    String data = response.getString(0);
                    detail = JSONObject.parseObject(data, SchoolDetail.class);
                    fillData(detail);
                }
            }


            @Override
            public void onResponseError(String error) {

            }
        });
    }


    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_school_info;
    }


    private void fillData(SchoolDetail detail) {
        if (detail == null)
            return;
        if (!TextUtils.isEmpty(detail.getDri_file_path())) {
            schoolinfoIvLogo.setImageURI(Uri.parse(detail.getDri_file_path()));
        }
        schoolinfoTvName.setText(detail.getDri_nm());
        schoolinfoTvPrice.setText(detail.getDri_money() + "元");
        if (!TextUtils.isEmpty(detail.getDri_tel1())
                && !TextUtils.isEmpty(detail.getDri_tel2())) {
            schoolinfoTvTel.setText(detail.getDri_tel1() + " " + detail.getDri_tel2());
        } else if (!TextUtils.isEmpty(detail.getDri_tel1())
                && TextUtils.isEmpty(detail.getDri_tel2())) {
            schoolinfoTvTel.setText(detail.getDri_tel1());
        } else if (TextUtils.isEmpty(detail.getDri_tel1())
                && !TextUtils.isEmpty(detail.getDri_tel2())) {
            schoolinfoTvTel.setText(detail.getDri_tel2());
        } else {
            schoolinfoTvTel.setText("暂时没有电话");
        }
        schoolinfoTvAddr.setText(detail.getDri_address());
        schoolinfoIvImgsCount.setText(detail.getStreet_view().size() + "张");
//        schoolinfoGvImgs

        schoolinfoTvFen.setText(detail.getDri_sum() + "分");
        schoolinfoTvRen.setText(detail.getCountpepole() + "人评分");

        schoolinfoTimeRating.setRating(detail.getDri_time());
        schoolinfoTimeFen.setText(detail.getDri_time() + "分");

        schoolinfoAddrRating.setRating(detail.getDri_place());
        schoolinfoAddrFen.setText(detail.getDri_place() + "分");

        schoolinfoPassRating.setRating(detail.getDri_pass());
        schoolinfoPassFen.setText(detail.getDri_pass() + "分");

        schoolinfoTvInfo.setText(detail.getDri_report());

        ImageAdapter adapte = new ImageAdapter(getContext(), detail.getStreet_view(), R.layout.item_school_grid);
        schoolinfoGvImgs.setAdapter(adapte);
        schoolinfoTvBus.setText(detail.getDri_way());
    }


    class ImageAdapter extends CommonAdapter<StreetView> {

        public ImageAdapter(Context context, List<StreetView> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, int position, StreetView item) {
            SimpleDraweeView icon = helper.getView(R.id.img);
            if (!TextUtils.isEmpty(item.getDri_file_path())) {
                icon.setImageURI(Uri.parse(item.getDri_file_path()));
            }
        }
    }

}

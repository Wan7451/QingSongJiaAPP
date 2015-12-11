package com.qingsongjia.qingsongjia.driverschool;

import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.SchoolDetail;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

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
    private int id;

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

            }
        });
    }

    private void loadData() {
        NetRequest.loadSchoolDeatail(getContext(), id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                if (!TextUtils.equals(response.toJSONString(), "[{}]")) {
                    String data = response.getString(0);
                    SchoolDetail detail = JSONObject.parseObject(data, SchoolDetail.class);
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
        schoolinfoIvImgsCount.setText(detail.getStreet_view().length+"张");
//        schoolinfoGvImgs

        schoolinfoTvFen.setText(detail.getDri_sum()+"分");
        schoolinfoTvRen.setText(detail.getCountpepole()+"人评分");

        schoolinfoTimeRating.setRating(detail.getDri_time());
        schoolinfoTimeRating.setMax(5);
        schoolinfoTimeFen.setText(detail.getDri_time()+"分");

        schoolinfoAddrRating.setRating(detail.getDri_place());
        schoolinfoAddrRating.setMax(5);
        schoolinfoAddrFen.setText(detail.getDri_place()+"分");

        schoolinfoPassRating.setRating(detail.getDri_pass());
        schoolinfoPassRating.setMax(5);
        schoolinfoPassFen.setText(detail.getDri_pass()+"分");

        schoolinfoTvInfo.setText(detail.getDri_report());

//        schoolinfoTvBus.setText(detail.getDri_way());
    }

}

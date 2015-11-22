package com.qingsongjia.qingsongjia.driverexam;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 科目二、三 练习详情
 */
public class ExamDetailActivity extends WanActivity {


    public static final String DETAIL_TYPE_AQD="AQD";//安全带
    public static final String DETAIL_TYPE_DHKG="DHKG";//点火开关
    public static final String DETAIL_TYPE_FXP="FXP";//方向盘
    public static final String DETAIL_TYPE_LHQ="LHQ";//离合器
    public static final String DETAIL_TYPE_JSTB="JSTB";//加速踏板
    public static final String DETAIL_TYPE_ZCZD="ZCZD";//驻车制动
    public static final String DETAIL_TYPE_ZYTZ="ZYTZ";//座椅调整
    public static final String DETAIL_TYPE_HSJ="HSJ";//后视镜
    public static final String DETAIL_TYPE_CJPD="CJPD";//车距判断
    public static final String DETAIL_TYPE_DWCZ="DWCZ";//档位操作
    public static final String DETAIL_TYPE_DG="DG";//灯光
    public static final String DETAIL_TYPE_ZX="ZX";//执行



    @Bind(R.id.examDetail_detailIcon)
    ImageView examDetailDetailIcon;
    @Bind(R.id.examDetail_detailName)
    TextView examDetailDetailName;
    @Bind(R.id.examDetail_detailStep)
    TextView examDetailDetailStep;
    @Bind(R.id.examDetail_detailContent)
    WebView examDetailDetailContent;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initSystemBar(0xFFF0640C);
        getTitleBar().setBackgroundColor(0xFFF0640C);
        setBackFinish();

        findViewById(R.id.title_back).setBackgroundDrawable(new ColorDrawable());

       String type= getIntent().getStringExtra("type");

        switch (type){
            case DETAIL_TYPE_AQD:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_aqd);
                examDetailDetailName.setText("安全带");
                break;
            case DETAIL_TYPE_DHKG:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_dhkg);
                examDetailDetailName.setText("点火开关");
                break;
            case DETAIL_TYPE_FXP:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_fxp);
                examDetailDetailName.setText("方向盘");
                break;
            case DETAIL_TYPE_LHQ:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_lhq);
                examDetailDetailName.setText("离合器");
                break;
            case DETAIL_TYPE_JSTB:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_jstb);
                examDetailDetailName.setText("加速踏板");
                break;
            case DETAIL_TYPE_ZCZD:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_zczd);
                examDetailDetailName.setText("驻车制动");
                break;
            case DETAIL_TYPE_ZYTZ:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_zytz);
                examDetailDetailName.setText("座椅调整");
                break;
            case DETAIL_TYPE_HSJ:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_hsj);
                examDetailDetailName.setText("后视镜");
                break;
            case DETAIL_TYPE_CJPD:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_cjpd);
                examDetailDetailName.setText("车距判断");
                break;
            case DETAIL_TYPE_DWCZ:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_dwcz);
                examDetailDetailName.setText("档位操作");
                break;
            case DETAIL_TYPE_DG:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_dg);
                examDetailDetailName.setText("灯光");
                break;
            case DETAIL_TYPE_ZX:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_zx);
                examDetailDetailName.setText("直行");
                break;
        }

        examDetailDetailContent.loadUrl("http://www.hao123.com");
        examDetailDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);   //在当前的webview中跳转到新的url

                return true;
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_exam_detail;
    }

}

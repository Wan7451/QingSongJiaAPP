package com.qingsongjia.qingsongjia.driverexam;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.webkit.WebSettings;
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


    public static final String DETAIL_TYPE_AQD = "AQD";//安全带
    public static final String DETAIL_TYPE_DHKG = "DHKG";//点火开关
    public static final String DETAIL_TYPE_FXP = "FXP";//方向盘
    public static final String DETAIL_TYPE_LHQ = "LHQ";//离合器
    public static final String DETAIL_TYPE_JSTB = "JSTB";//加速踏板
    public static final String DETAIL_TYPE_ZCZD = "ZCZD";//驻车制动
    public static final String DETAIL_TYPE_ZYTZ = "ZYTZ";//座椅调整
    public static final String DETAIL_TYPE_HSJ = "HSJ";//后视镜
    public static final String DETAIL_TYPE_CJPD = "CJPD";//车距判断
    public static final String DETAIL_TYPE_DWCZ = "DWCZ";//档位操作
    public static final String DETAIL_TYPE_DG = "DG";//灯光
    public static final String DETAIL_TYPE_ZX = "ZX";//执行


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
        initSystemBar(0xFFF07F60);
        getTitleBar().setBackgroundColor(0xFFF07F60);
        setBackFinish();
        String title = getIntent().getStringExtra("title");
        setContentTitle(title);

        findViewById(R.id.title_back).setBackgroundDrawable(new ColorDrawable());

        String type = getIntent().getStringExtra("type");

        String path1 = "file:///android_asset/html/kemu2/hint/";
        String path2 = "file:///android_asset/html/kemu3/hint/";
        switch (type) {
            case DETAIL_TYPE_AQD:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_aqd);
                examDetailDetailName.setText("安全带");
                examDetailDetailContent.loadUrl(path1 + "anquandai.html");
                break;
            case DETAIL_TYPE_DHKG:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_dhkg);
                examDetailDetailName.setText("点火开关");
                examDetailDetailContent.loadUrl(path1 + "dianhuokaiguan.html");
                break;
            case DETAIL_TYPE_FXP:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_fxp);
                examDetailDetailContent.loadUrl(path1 + "fangxiangpan.html");
                examDetailDetailName.setText("方向盘");
                break;
            case DETAIL_TYPE_LHQ:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_lhq);
                examDetailDetailContent.loadUrl(path1 + "liheqi.html");
                examDetailDetailName.setText("离合器");
                break;
            case DETAIL_TYPE_JSTB:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_jstb);
                examDetailDetailContent.loadUrl(path1 + "jiasutaban.html");
                examDetailDetailName.setText("加速踏板");
                break;
            case DETAIL_TYPE_ZCZD:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_zczd);
                examDetailDetailContent.loadUrl(path1 + "zhuchezhidong.html");
                examDetailDetailName.setText("驻车制动");
                break;
            case DETAIL_TYPE_ZYTZ:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_zytz);
                examDetailDetailContent.loadUrl(path1 + "zuoyitiaozheng.html");
                examDetailDetailName.setText("座椅调整");
                break;
            case DETAIL_TYPE_HSJ:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_hsj);
                examDetailDetailContent.loadUrl(path1 + "houshijing.html");
                examDetailDetailName.setText("后视镜");
                break;
            case DETAIL_TYPE_CJPD:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_cjpd);
                examDetailDetailName.setText("车距判断");
                examDetailDetailContent.loadUrl(path2 + "chejupanduan.html");
                break;
            case DETAIL_TYPE_DWCZ:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_dwcz);
                examDetailDetailName.setText("档位操作");
                examDetailDetailContent.loadUrl(path2 + "dangweicaozong.html");
                break;
            case DETAIL_TYPE_DG:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_dg);
                examDetailDetailName.setText("灯光");
                examDetailDetailContent.loadUrl(path2 + "dengguang.html");
                break;
            case DETAIL_TYPE_ZX:
                examDetailDetailIcon.setImageResource(R.drawable.icon_examdetail_zx);
                examDetailDetailName.setText("直行");
                examDetailDetailContent.loadUrl(path2 + "zhixing.html");
                break;
        }

        WebSettings webSettings = examDetailDetailContent.getSettings();
        // webView: 类WebView的实例
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);  //就是这句
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        examDetailDetailContent.setWebViewClient(new WebViewClient() {
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

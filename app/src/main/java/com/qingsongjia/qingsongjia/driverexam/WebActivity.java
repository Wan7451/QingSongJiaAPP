package com.qingsongjia.qingsongjia.driverexam;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends WanActivity {


    public static final int TYPE_BMXZ = 1;
    public static final int TYPE_JTFG = 2;
    public static final int TYPE_XSSL = 3;

    int type = 0;
    @Bind(R.id.webView)
    WebView webView;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
         type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case TYPE_BMXZ:
                setContentTitle("报名须知");
                break;
            case TYPE_JTFG:
                setContentTitle("交通法规");
                break;
            case TYPE_XSSL:
                setContentTitle("新手上路");
                break;
        }

        webView.loadUrl("http://www.hao123.com");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);   //在当前的webview中跳转到新的url

                return true;
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_web;
    }

}

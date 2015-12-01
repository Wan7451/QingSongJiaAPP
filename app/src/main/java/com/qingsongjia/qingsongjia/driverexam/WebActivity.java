package com.qingsongjia.qingsongjia.driverexam;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends WanActivity {


    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.progress)
    ProgressBar progressbar;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();

        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        setContentTitle(title);

        if(url.endsWith("html")){
            webView.loadUrl(url);
        }else {
            String data = "<HTML><IMG src=\"" + url + "\"" + "style=\"width:100%;height:auto\"/>";
            webView.loadDataWithBaseURL(url, data, "text/html", "utf-8", null);
            webView.getSettings().setBuiltInZoomControls(true); //显示放大缩小 controler
            webView.getSettings().setSupportZoom(true); //可以缩放
        }



        WebSettings webSettings = webView.getSettings();
        // webView: 类WebView的实例
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);  //就是这句
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);   //在当前的webview中跳转到新的url

                return true;
            }


        });
        webView.setWebChromeClient(new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressbar.setVisibility(View.GONE);
                } else {
                    if (progressbar.getVisibility() == View.GONE)
                        progressbar.setVisibility(View.VISIBLE);
                    progressbar.setProgress(newProgress);
                }
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_web;
    }

}

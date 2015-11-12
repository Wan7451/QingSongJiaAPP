package com.wan7451.advancedview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wan7451.utils.NetUtils;
import com.wan7451.wanadapter.mylibrary.R;


/**
 * Created by Hello on 2015/7/6.
 */
public class ErrorLayoutView extends LinearLayout implements View.OnClickListener {

    /**
     * 未知错误
     */
    public static final int NETWORK_ERROR = 1;

    /**
     * 加载中
     */
    public static final int NETWORK_LOADING = 2;

    /**
     * 没有数据
     */
    public static final int NODATA = 3;

    /**
     * 隐藏布局
     */
    public static final int HIDE_LAYOUT = 4;

    /**
     * 没有数据点击加载
     */
    public static final int NODATA_ENABLE_CLICK = 5;


    private TextView errorTextView;
    private ImageView errorIconView;
    private ProgressBar errorProgress;
    private Button errorButton;
    private OnClickListener listener;
    private int mErrorState;
    private boolean clickEnable = true;
    private String strNoDataContent;


    public ErrorLayoutView(Context context) {
        super(context);
        init();
    }

    public ErrorLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_error_view, null);

        errorTextView = (TextView) view.findViewById(R.id.error_layout_text);
        errorIconView = (ImageView) view.findViewById(R.id.error_layout_img);
        errorProgress = (ProgressBar) view.findViewById(R.id.error_layout_animProgress);
        errorButton = (Button) view.findViewById(R.id.error_layout_button);
        errorIconView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickEnable && listener != null) {
                    listener.onClick(v);
                }
            }
        });
        setOnClickListener(this);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        view.setLayoutParams(lp);
        addView(view);
    }

    public void setErrorButton(String text, OnClickListener l) {
        errorButton.setVisibility(VISIBLE);
        errorButton.setText(text);
        errorButton.setOnClickListener(l);
    }

    public void dismiss() {
        mErrorState = HIDE_LAYOUT;
        setVisibility(View.GONE);
    }

    public int getErrorState() {
        return mErrorState;
    }

    public boolean isLoadError() {
        return mErrorState == NETWORK_ERROR;
    }

    public boolean isLoading() {
        return mErrorState == NETWORK_LOADING;
    }


    @Override
    public void onClick(View v) {
        if (clickEnable) {
            // setErrorType(NETWORK_LOADING);
            if (listener != null)
                listener.onClick(v);
        }
    }


    public void setErrorMessage(String msg) {
        errorTextView.setText(msg);
    }

    public void setErrorImag(int imgResource) {
        try {
            errorIconView.setImageResource(imgResource);
        } catch (Exception e) {
        }
    }

    public void setErrorType(int i) {
        setVisibility(View.VISIBLE);
        switch (i) {
            case NETWORK_ERROR:
                mErrorState = NETWORK_ERROR;
                // img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"pagefailed_bg"));
                if (NetUtils.hasInternet(getContext())) {
                    errorTextView.setText("加载数据失败，点击重新加载");
                    //img.setBackgroundResource(); TODO 设置图片
                } else {
                    errorTextView.setText("网络没有连接，请开启网络");
                    //img.setBackgroundResource(); TODO  设置图片
                }
                errorIconView.setVisibility(View.VISIBLE);
                errorProgress.setVisibility(View.GONE);
                clickEnable = true;
                break;
            case NETWORK_LOADING:
                mErrorState = NETWORK_LOADING;
                // animProgress.setBackgroundDrawable(SkinsUtil.getDrawable(context,"loadingpage_bg"));
                errorProgress.setVisibility(View.VISIBLE);
                errorIconView.setVisibility(View.GONE);
                errorTextView.setText("数据加载中");
                clickEnable = false;
                break;
            case NODATA:
                mErrorState = NODATA;
                // img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"page_icon_empty"));
                // img.setBackgroundResource();TODO  设置图片
                errorIconView.setVisibility(View.VISIBLE);
                errorProgress.setVisibility(View.GONE);
                setTvNoDataContent();
                clickEnable = true;
                break;
            case HIDE_LAYOUT:
                setVisibility(View.GONE);
                break;
            case NODATA_ENABLE_CLICK:
                mErrorState = NODATA_ENABLE_CLICK;
                //img.setBackgroundResource(); TODO  设置图片
                // img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"page_icon_empty"));
                errorIconView.setVisibility(View.VISIBLE);
                errorProgress.setVisibility(View.GONE);
                setTvNoDataContent();
                clickEnable = true;
                break;
            default:
                break;
        }
    }

    public void setNoDataContent(String noDataContent) {
        strNoDataContent = noDataContent;
    }

    public void setOnLayoutClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setTvNoDataContent() {
        if (!TextUtils.isEmpty(strNoDataContent) && !strNoDataContent.equals(""))
            errorTextView.setText(strNoDataContent);
        else
            errorTextView.setText("目前没有数据");
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE)
            mErrorState = HIDE_LAYOUT;
        super.setVisibility(visibility);
    }


}

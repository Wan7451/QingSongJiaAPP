package com.wan7451.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wan7451.advancedview.ErrorLayoutView;
import com.wan7451.formview.WanTextView;
import com.wan7451.wanadapter.mylibrary.R;

/**
 * Created by Administrator on 2015/10/19.
 */
public abstract class WanFragment extends Fragment implements INetLoadAction {

    private ErrorLayoutView errorView;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_root_layout, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.fragment_base_toolbar);
        if (isShowTitleView()) {
            //去掉左边距
            toolbar.setContentInsetsAbsolute(0, 0);
            getWanActivity().setSupportActionBar(toolbar);
        } else {
            toolbar.setVisibility(View.GONE);
        }

        toolbar.setScrollContainer(false);

        ViewGroup mainView = (ViewGroup) rootView.findViewById(R.id.fragment_base_mainview);
        View mainContent = inflater.inflate(getMainViewLayoutId(), mainView, false);
        mainView.addView(mainContent);


        initView(mainContent);

        return rootView;

    }


    private WanActivity getWanActivity() {
        return (WanActivity) context;
    }

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * 初始化UI组件
     */
    protected abstract void initView(View view);

    protected abstract int getMainViewLayoutId();


    /**
     * 是否显示标题栏
     *
     * @return true 显示标题 false 不显示标题
     */
    protected boolean isShowTitleView() {
        return true;
    }

    public Context getContext() {
        return context;
    }

    //-----------------错误视图--------------------
    public View getErrorLayoutView() {
        return errorView;
    }

    public void setErrorText(String text, View.OnClickListener l) {
        errorView.setVisibility(View.VISIBLE);
        ErrorLayoutView errorLayoutView = (ErrorLayoutView) errorView.findViewById(R.id.errorLayoutView);
        errorLayoutView.setErrorMessage(text);
        if (l != null) {
            errorLayoutView.setOnLayoutClickListener(l);
        }
    }


    //----------------Toast提示-------------
    public void showToast(String showText) {
        getAppContext().showToast(showText);
    }

    public void showLongToast(String showText) {
        getAppContext().showLongToast(showText);
    }


    //-----------------标题栏设置--------------------

    public View getTitleBar() {
        return toolbar;
    }

    public void setContentTitle(String title) {
        WanTextView titleView = (WanTextView) getTitleBar().findViewById(R.id.title_text);
        if (titleView != null)
            titleView.setText(title);
    }


    /**
     * 设置右边按钮文字
     *
     * @param text     显示的文字
     * @param listener 点击事件
     */
    public void setRightText(String text, View.OnClickListener listener) {
        WanTextView rightBtn = (WanTextView) getTitleBar().findViewById(R.id.title_right);
        rightBtn.setVisibility(View.VISIBLE);
        rightBtn.setText(text);
        if (listener != null)
            rightBtn.setOnClickListener(listener);
    }


    /**
     * 设置左边按钮文字
     *
     * @param text     显示的文字
     * @param listener 点击事件
     */
    public void setLeftText(String text, View.OnClickListener listener) {
        WanTextView rightBtn = (WanTextView) getTitleBar().findViewById(R.id.title_left);
        rightBtn.setVisibility(View.VISIBLE);
        rightBtn.setText(text);
        if (listener != null)
            rightBtn.setOnClickListener(listener);
    }

    /**
     * 设置右边按钮文字
     *
     * @param text 显示的文字
     */
    public void setRightText(String text) {
        WanTextView rightBtn = (WanTextView) getTitleBar().findViewById(R.id.title_right);
        rightBtn.setVisibility(View.VISIBLE);
        rightBtn.setText(text);
    }

    public void hineLeftIcon() {
        ImageView backView = (ImageView) getTitleBar().findViewById(R.id.title_back);
        backView.setVisibility(View.GONE);
    }

    /**
     * 设置左边图标
     */
    public void setLeftIcon(int resId, View.OnClickListener listener) {
        ImageView backView = (ImageView) getTitleBar().findViewById(R.id.title_back);
        if (backView != null) {
            backView.setVisibility(View.VISIBLE);
            backView.setImageResource(resId);
            backView.setOnClickListener(listener);
        }
    }

    /**
     * 设置返回键退出
     */
    public void setBackFinish(int icon) {
        ImageView backView = (ImageView) getTitleBar().findViewById(R.id.title_back);
        backView.setVisibility(View.VISIBLE);
        backView.setImageResource(icon);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWanActivity().finish();
            }
        });
    }

    /**
     * 设置返回键退出
     */
    public void setBackFinish(final View.OnClickListener l) {
        ImageView backView = (ImageView) getTitleBar().findViewById(R.id.title_back);
        backView.setVisibility(View.VISIBLE);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWanActivity().finish();
                if (l != null)
                    l.onClick(v);
            }
        });
    }


    //获得屏幕宽度
    public int getWindowWidthPx() {
        return getWanActivity().getWindowWidthPx();
    }


    /**
     * 返回Application
     */
    public WanApplication getAppContext() {
        return (WanApplication) getWanActivity().getApplication();
    }

    public void colseActivity(Class<? extends Activity> activity) {
        getAppContext().closeActivity(activity);
    }

    @Override
    public void onLoading() {
        errorView.setErrorType(ErrorLayoutView.NETWORK_LOADING);
    }

    @Override
    public void onLoadedFinish() {
        errorView.setErrorType(ErrorLayoutView.HIDE_LAYOUT);
    }

    @Override
    public void onLoadedNoData() {
        errorView.setErrorType(ErrorLayoutView.NODATA_ENABLE_CLICK);
    }

    @Override
    public void onLoadedError() {
        errorView.setErrorType(ErrorLayoutView.NETWORK_ERROR);
    }

    public void setErrorMessage(ErrorMessage msg) {
        if (msg != null) {
            if (!TextUtils.isEmpty(msg.getMessage())) {
                errorView.setErrorMessage(msg.getMessage());
            }
            if (msg.getErrorIcon() != 0) {
                errorView.setErrorImag(msg.getErrorIcon());
            }

            if (msg.getErrorBtnText() != null) {
            }
        }
    }
}

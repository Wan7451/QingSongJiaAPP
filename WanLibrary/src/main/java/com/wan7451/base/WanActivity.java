package com.wan7451.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wan7451.advancedview.ErrorLayoutView;
import com.wan7451.formview.WanTextView;
import com.wan7451.wanadapter.mylibrary.R;

/**
 * Created by Administrator on 2015/10/19.
 */
public abstract class WanActivity extends AppCompatActivity implements INetLoadAction {


    private ErrorLayoutView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getAppContext().addAcitivty(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_base_toolbar);
        if (isShowTitleView()) {
            //去掉左边距
            toolbar.setContentInsetsAbsolute(0, 0);
            setSupportActionBar(toolbar);
        } else {
            toolbar.setVisibility(View.GONE);
        }

        toolbar.setScrollContainer(false);

        initSystemBar();

        ViewGroup mainView = (ViewGroup) findViewById(R.id.activity_base_mainview);
        View mainContent = getLayoutInflater().inflate(getMainViewLayoutId(), mainView, false);
        mainView.addView(mainContent);

        errorView = (ErrorLayoutView) findViewById(R.id.activity_base_errorView);

        initView();
        getAppContext().addAcitivty(this);
    }


    /**
     * 初始化UI组件
     */
    protected void initView() {
    }

    protected abstract int getMainViewLayoutId();


    /**
     * 是否显示标题栏
     *
     * @return true 显示标题 false 不显示标题
     */
    protected boolean isShowTitleView() {
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getAppContext().deleteActivity(this);
    }


    public void setBackFinish() {
        setLeftIcon(R.drawable.icon_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public WanActivity getContext() {
        return this;
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

    //----------------状态栏沉浸-------------
    private SystemBarTintManager.SystemBarConfig config;

    protected void initSystemBar() {
        initSystemBar(0xFFE46451);
    }

    public void initSystemBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setTintColor(color);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(color);
            View v = findViewById(android.R.id.text1);
            if (v != null && config == null) {
                config = tintManager.getConfig();
                v.setPadding(0, config.getPixelInsetTop(true), 0, 0);
            }
        }
    }

    //----------------Toast提示-------------

    private Toast mToast;

    public void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void showLongToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public void onBackPressed() {
        cancelToast();
        super.onBackPressed();
    }

    //-----------------标题栏设置--------------------
    private View mTitlebar;

    public View getTitleBar() {
        if (mTitlebar == null) {
            mTitlebar = findViewById(R.id.title);
        }
        return mTitlebar;
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
                finish();
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
                finish();
                if (l != null)
                    l.onClick(v);
            }
        });
    }


    //获得屏幕宽度
    public int getWindowWidthPx() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }


    /**
     * 返回Application
     */
    public WanApplication getAppContext() {
        return (WanApplication) getApplication();
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
//        LinearLayout rootView = new LinearLayout(this);
//        rootView.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//        rootView.setOrientation(LinearLayout.VERTICAL);
//        rootView.setId(android.R.id.text1);
//        //是否显示标题
//        if (isShowTitleView()) {
//            View titleView = getLayoutInflater().inflate(R.layout.layout_title_view, null);
//            rootView.addView(titleView);
//        }
//
//        FrameLayout mainView = new FrameLayout(this);
//        mainView.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//        //主要内容视图
//        View contentView = getLayoutInflater().inflate(getMainView(), null);
//        contentView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT));
//        mainView.addView(contentView);
//        //错误视图
//        errorView = new ViewStub(this);
//        errorView.setLayoutResource(R.layout.layout_error_layout);
//        errorView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT));
//        mainView.addView(errorView);
//        rootView.addView(mainView);
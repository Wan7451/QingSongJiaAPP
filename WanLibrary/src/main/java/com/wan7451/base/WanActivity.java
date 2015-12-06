package com.wan7451.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
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
        super.onCreate(savedInstanceState);
        setContentView(getMainRootLayout());

        View toolbar = findViewById(R.id.activity_base_toolbar);
        if (toolbar != null) {
            if (!isShowTitleView()) {
                toolbar.setVisibility(View.GONE);
            }
        }

        initSystemBar();

        ViewGroup mainView = (ViewGroup) findViewById(R.id.activity_base_mainview);
        if (mainView != null) {
            View mainContent = getLayoutInflater().inflate(getMainViewLayoutId(), mainView, false);
            mainView.addView(mainContent);
        }

        errorView = (ErrorLayoutView) findViewById(R.id.activity_base_errorView);

        initView();
        getAppContext().addAcitivty(this);
    }


    int getMainRootLayout() {
        return R.layout.activity_root_layout;
    }




    /**
     * 初始化UI组件
     */
    public abstract void initView();

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
        initSystemBar(getResources().getColor(R.color.title_bar_color));
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
            mTitlebar = findViewById(R.id.activity_base_toolbar);
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

    //-----------------收发广播--------------------
    public void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    public void unregisterLocalReceiver(BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    public void sendBrocadcast(Intent intent) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
package com.wan7451.base;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.wan7451.advancedview.ErrorLayoutView;
import com.wan7451.wanadapter.mylibrary.R;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanRecycleView;

/**
 * Created by Administrator on 2015/10/19.
 */
public abstract class WanListActivity extends WanActivity implements PullToRefreshBase.OnRefreshListener2, WanAdapter.OnItemClickListener {

    private WanRecycleView main;
    private ErrorLayoutView errorView;
    private WanAdapter mAdapter;
    private WanItemDecoration itemDecore;
    public int total;
    private boolean isSupportBoth; //是否支持上下拉刷新


    protected abstract boolean addData();


    public abstract WanAdapter getAdapter();


    /**
     * 返回使用的布局，默认仅含有列表组件
     *
     * @return
     */
    public int getMyLayout() {
        return 0;
    }

    /**
     * List头部不滑动的View
     *
     * @return
     */
    public View getListHeaderView() {
        return null;
    }

    @Override
    int getMainRootLayout() {
        return getMyLayout() != 0 ? getMyLayout() : R.layout.activity_root_list;
    }

    /**
     * 初始化View
     */
    public void initView() {
        main = (WanRecycleView) findViewById(R.id.activity_mainView);
        errorView = (ErrorLayoutView) findViewById(R.id.activity_base_errorView);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                errorView.setErrorType(ErrorLayoutView.HIDE_LAYOUT);
                main.setRefreshing();
            }
        });
        mAdapter = getAdapter();
        main.getRefreshableView().setAdapter(mAdapter);
        main.getRefreshableView().setLayoutManager(new LinearLayoutManager(getContext()));
        itemDecore = new WanItemDecoration(getContext(), WanItemDecoration.VERTICAL_LIST);
        main.getRefreshableView().addItemDecoration(itemDecore);
        main.setOnRefreshListener(this);
        main.setScrollingWhileRefreshingEnabled(true);

        ViewGroup mainView = (ViewGroup) findViewById(R.id.activity_main_headerView);
        View headerView = getListHeaderView();
        if (mainView != null && headerView != null) {
            mainView.addView(headerView);
        }

        if (mAdapter != null)
            mAdapter.setOnItemClickListener(this);
    }


    private boolean isOnStartedShow = true;

    /**
     * 设置是否在界面启动的时候加载
     *
     * @param isOnStartedShow
     */
    public void setIsOnStartedShow(boolean isOnStartedShow) {
        this.isOnStartedShow = isOnStartedShow;
    }


    private  boolean isShowLocalData;
    /**
     * 显示本地数据
     */
    public void setIsShowLocalData(){
        isShowLocalData=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter == null)
            return;

        if(isShowLocalData){
            main.setMode(PullToRefreshBase.Mode.DISABLED);
            loadData();
            mAdapter.notifyDataSetChanged();
            return;
        }

        if (isOnStartedShow && mAdapter.getDatas() != null && mAdapter.getDatas().size() == 0)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    main.setRefreshing();
                }
            }, 300);
    }

    protected abstract void loadData();


    public void loadFinish(final String emptyHint) {

        if (total != 0 && total != 20) {
            main.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        } else {
            if (isSupportBoth)
                main.setMode(PullToRefreshBase.Mode.BOTH);
            else
                main.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }

        main.onRefreshComplete();
        mAdapter.notifyDataSetChanged();
        if (mAdapter.getDatas().size() == 0) {
            //数据空
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    errorView.setVisibility(View.VISIBLE);
                    errorView.setErrorType(ErrorLayoutView.NODATA);
                    errorView.setErrorMessage(emptyHint);
                    errorView.setErrorImag(R.drawable.bow_doctor_zheng1);
                }
            }, 300);
        } else {
            errorView.dismiss();
        }
    }


    public void loadFinish(final String emptyHint, final String text, final View.OnClickListener l) {
        if (total != 0 && total != 20) {
            main.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        } else {
            if (isSupportBoth)
                main.setMode(PullToRefreshBase.Mode.BOTH);
            else
                main.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }

        main.onRefreshComplete();
        mAdapter.notifyDataSetChanged();
//        mAdapter.notifyItemRangeInserted(currCount,mAdapter.getDatas().size());
//        main.getRefreshableView().scrollToPosition(currCount);
        if (mAdapter.getDatas().size() == 0) {
            //数据空
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    errorView.setVisibility(View.VISIBLE);
                    errorView.setErrorType(ErrorLayoutView.NODATA);
                    errorView.setErrorMessage(emptyHint);
                    errorView.setErrorImag(R.drawable.bow_doctor_zheng1);
                    errorView.setErrorButton(text, l);

                }
            }, 300);

        } else {
            errorView.dismiss();
        }
    }


    public void loadError() {
        main.onRefreshComplete();
        errorView.setVisibility(View.VISIBLE);
        errorView.setErrorMessage("加载数据失败");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        loadData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        addData();
    }


    public boolean isSupportBoth() {
        return isSupportBoth;
    }

    public void setIsSupportBoth(boolean isSupportBoth) {
        this.isSupportBoth = isSupportBoth;
    }

    public WanRecycleView getMainView() {
        return main;
    }

    public ErrorLayoutView getErrorView() {
        return errorView;
    }

    public WanAdapter getMainAdapter() {
        return mAdapter;
    }

    public WanItemDecoration getItemDecore() {
        return itemDecore;
    }

}

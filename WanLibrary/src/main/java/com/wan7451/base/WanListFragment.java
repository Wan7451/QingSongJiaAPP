package com.wan7451.base;

import android.view.View;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.wan7451.advancedview.ErrorLayoutView;
import com.wan7451.wanadapter.mylibrary.R;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanRecycleView;
import com.wan7451.wanadapter.recycle.WanViewHolder;

/**
 * Created by Administrator on 2015/8/31.
 */
public abstract class WanListFragment extends WanFragment implements PullToRefreshBase.OnRefreshListener2, WanAdapter.OnItemClickListener {

    private WanRecycleView main;
    private ErrorLayoutView errorView;
    private WanAdapter mAdapter;
    private WanItemDecoration itemDecore;
    private int currCount;
    public int total;
    private boolean isSupportBoth = false;



    protected abstract boolean addData();
    protected abstract boolean loadData();

    public abstract WanAdapter getAdapter();

    /**
     * List头部不滑动的View
     * @return
     */
    public View getListHeaderView() {
        return null;
    }

    /**
     * 初始化View
     */
    public void initView(View v) {
        main = (WanRecycleView) v.findViewById(R.id.fragment_mainView);
        errorView = (ErrorLayoutView) v.findViewById(R.id.fragment_base_errorView);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                errorView.setErrorType(ErrorLayoutView.HIDE_LAYOUT);
//                main.setRefreshing();
//                loadData();
            }
        });
        mAdapter = getAdapter();
        main.getRefreshableView().setAdapter(mAdapter);
        main.getRefreshableView().setLayoutManager(new LinearLayoutManager(getContext()));

        itemDecore = new WanItemDecoration(getContext(), WanItemDecoration.VERTICAL_LIST);
        main.getRefreshableView().addItemDecoration(itemDecore);
        main.setOnRefreshListener(this);
        main.setScrollingWhileRefreshingEnabled(true);

        if (mAdapter != null)
            mAdapter.setOnItemClickListener(this);

        View headerView=getListHeaderView();
        if (v != null && headerView != null) {
            ViewGroup group = (ViewGroup) v.findViewById(R.id.fragment_main_headerView);
            group.addView(headerView);
            group.invalidate();
        }

        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }

    }

    @Override
    protected boolean isShowTitleView() {
        return false;
    }

    //主View,当前无效
    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    /**
     * 返回使用的布局，默认仅含有列表组件
     *
     * @return
     */
    public int getMyLayout() {
        return 0;
    }

    @Override
   public int getMainRootLayout() {
        return getMyLayout() != 0 ? getMyLayout() : R.layout.fragment_root_list;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (mAdapter == null)
            return;

        if (mAdapter.getDatas() != null && mAdapter.getDatas().size() == 0)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    main.setRefreshing();
                }
            }, 300);

        if (total != 0 && total != 20) {
            main.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        } else {
            if (isSupportBoth)
                main.setMode(PullToRefreshBase.Mode.BOTH);
            else
                main.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }
    }

    public void loadFinish(final String emptyHint, boolean isShowEmptyView) {
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
            if (isShowEmptyView)
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


    public void loadError(String error) {
        main.onRefreshComplete();
        errorView.setErrorMessage(error);
        errorView.setErrorType(ErrorLayoutView.NETWORK_ERROR);
    }

    public void loadError() {
        main.onRefreshComplete();
        errorView.setErrorMessage("加载数据失败");
        errorView.setErrorType(ErrorLayoutView.NETWORK_ERROR);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        loadData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        currCount = mAdapter.getDatas().size();
        addData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getMainView().isRefreshing())
            getMainView().onRefreshComplete();
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

    public WanAdapter getMainAdapter() {
        return mAdapter;
    }

    public ErrorLayoutView getErrorView() {
        return errorView;
    }

    public WanItemDecoration getItemDecore() {
        return itemDecore;
    }

}


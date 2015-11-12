package com.wan7451.wanadapter.recycle;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.FrameLoadingLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

/**
 * 可以刷新的RecycleView
 * Created by Wan7451 on 2015/6/30.
 */
public class WanRecycleView extends PullToRefreshBase<BaseRecycleView> {

    public WanRecycleView(Context context) {
        super(context);
    }

    public WanRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WanRecycleView(Context context, Mode mode) {
        super(context, mode);
    }

    public WanRecycleView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
        getRefreshableView().invalidate();
    }

    /**
     * 自动支持上拉加载
     * 当ItemView撑满RecycleView高度后，自动添加下拉刷新
     * 当不足的时候，默认上拉
     */
    public void setSupportModeBoth() {
        View item = getLastVisibileItem();
        if (item != null) {
            if (item.getBottom() > getBottom()) {
                setMode(PullToRefreshBase.Mode.BOTH);
            }
        }

    }

    /**
     * 通过 LinearLayoutManager 获得第一个可见的ItemView
     *
     * @return itemView
     */
    public View getFirstVisibileItem() {
        RecyclerView.LayoutManager lm = getRefreshableView().getLayoutManager();
        if (lm != null && lm instanceof LinearLayoutManager) {
            int posotion = ((LinearLayoutManager) lm).findFirstVisibleItemPosition();
            RecyclerView.ViewHolder holder = getRefreshableView().findViewHolderForLayoutPosition(posotion);
            if (holder instanceof WanViewHolder) {
                return ((WanViewHolder) holder).getConvertView();
            }
        }
        return null;
    }

    /**
     * 通过 LinearLayoutManager 获得最后一个可见的ItemView
     *
     * @return itemView
     */
    public View getLastVisibileItem() {
        RecyclerView.LayoutManager lm = getRefreshableView().getLayoutManager();
        if (lm != null && lm instanceof LinearLayoutManager) {
            int posotion = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
            RecyclerView.ViewHolder holder = getRefreshableView().findViewHolderForLayoutPosition(posotion);
            if (holder instanceof WanViewHolder) {
                return ((WanViewHolder) holder).getConvertView();
            }
        }
        return null;
    }

    //重写4个方法
    //1 滑动方向
    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    //重写4个方法
    //2  滑动的View
    @Override
    protected BaseRecycleView createRefreshableView(Context context, AttributeSet attrs) {
        BaseRecycleView view = new BaseRecycleView(context, attrs);
        return view;
    }

    static void brandGlowEffect(Context context, int brandColor) {
        try {
            //glow
            int glowDrawableId = context.getResources().getIdentifier("overscroll_glow", "drawable", "android");
            Drawable androidGlow = context.getResources().getDrawable(glowDrawableId);
            androidGlow.setColorFilter(brandColor, PorterDuff.Mode.SRC_IN);
            //edge
            int edgeDrawableId = context.getResources().getIdentifier("overscroll_edge", "drawable", "android");
            Drawable androidEdge = context.getResources().getDrawable(edgeDrawableId);
            androidEdge.setColorFilter(brandColor, PorterDuff.Mode.SRC_IN);

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    //重写4个方法
    //3 是否滑动到底部
    @Override
    protected boolean isReadyForPullEnd() {
//        View view = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
//        if (null != view) {
//            return getRefreshableView().getBottom() >= view.getBottom();
//        }
//        return false;

        if (getRefreshableView() == null)
            return false;

        if (getRefreshableView().getChildCount() <= 0)
            return true;

        //最后一个ChildView
        View childView = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
        if (childView != null) {
            int lastVisiblePosition = getRefreshableView().getChildPosition(childView);
            if (lastVisiblePosition >= getRefreshableView().getAdapter().getItemCount() - 1) {
                return childView.getBottom() <= getRefreshableView().getBottom();
            }
        }
        return false;
    }

    //重写4个方法
    //4 是否滑动到顶部
    @Override
    protected boolean isReadyForPullStart() {
//        View view = getRefreshableView().getChildAt(0);
//
//        if (view != null) {
//            return view.getTop() >= getRefreshableView().getTop();
//        }
//        return false;

        if (getRefreshableView().getChildCount() <= 0)
            return true;
        int firstVisiblePosition = getRefreshableView().getChildPosition(getRefreshableView().getChildAt(0));
        if (firstVisiblePosition == 0)
            return getRefreshableView().getChildAt(0).getTop() == getRefreshableView().getPaddingTop();
        else
            return false;

    }

}

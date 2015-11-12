package com.wan7451.wanadapter.recycle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

/**
 * 支持侧滑的ItemView
 * Created by Wan7451 on 2015/7/24.
 */
public class SwiftItemView extends FrameLayout {

    public static final int SWIFT_DISTANCE = 150;

    private View shownView;
    private float currX;
    private float currY;
    private int inAnim;
    private int outAnim;
    private OnClickListener l;

    private boolean isSwiftEnable=false;

    public View getShowView() {
        return shownView;
    }

    /**
     * 是否可以滑动触发
     *
     * @return
     */
    public boolean isSwiftEnable() {
        return isSwiftEnable;
    }

    /**
     * 是否可以滑动触发
     *
     * @return
     */
    public void setIsSwiftEnable(boolean isSwiftEnable) {
        this.isSwiftEnable = isSwiftEnable;
    }

    public void setOnClickListener(OnClickListener l) {
        this.l = l;
    }

    /**
     * 设置显示的View
     *
     * @param shownView
     */
    public void setShowView(View shownView) {
        this.shownView = shownView;
    }

    /**
     * 设置显示的View的进入退出的动画
     *
     * @param inAnim
     * @param outAnim
     */
    public void setInOutAnimation(int inAnim, int outAnim) {
        this.inAnim = inAnim;
        this.outAnim = outAnim;
    }

    /**
     * 设置显示的ViewID
     *
     * @param shownViewId
     */
    public void setShowView(int shownViewId) {
        this.shownView = findViewById(shownViewId);
    }

    public SwiftItemView(Context context) {
        super(context);
    }

    public SwiftItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(false);
                currX = event.getX();
                currY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                if (Math.abs(currX - event.getX()) > 100) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                if (currX - event.getX() > SWIFT_DISTANCE) {
                    if (shownView != null && isSwiftEnable)
                        if (shownView.getVisibility() != View.VISIBLE) {
                            Animation a = AnimationUtils.loadAnimation(getContext(), inAnim);
                            shownView.startAnimation(a);
                            shownView.setVisibility(View.VISIBLE);
                        }
                    return true;
                } else if (event.getX() - currX > SWIFT_DISTANCE) {
                    if (shownView != null && isSwiftEnable)
                        if (shownView.getVisibility() == View.VISIBLE) {
                            Animation a = AnimationUtils.loadAnimation(getContext(), outAnim);
                            shownView.startAnimation(a);
                            shownView.setVisibility(View.GONE);
                        }
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                if (event.getX() == currX && event.getY() == currY) {
                    if (l != null) {
                        l.onClick(this);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);
    }
}

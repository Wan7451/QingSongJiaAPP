package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * 帧动画
 * Created by Hello on 2015/7/8.
 */
public class FrameLoadingLayout extends LoadingLayout {

    private AnimationDrawable animationDrawable;


    public FrameLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);

        switch (context.getPackageName()) {
            case "com.yongbaojiankang.dangmala":
                mHeaderImage.setImageResource(R.drawable.frame_anim);
                break;

            case "com.yongbaojiankang.dangmaladoctor":
                mHeaderImage.setImageResource(R.drawable.frame_doctor_anim);
                break;
        }
        animationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();
    }



    @Override
    protected int getDefaultDrawableResId() {
        switch (getContext().getPackageName()) {
            case "com.yongbaojiankang.dangmala":
                return R.drawable.bow_zheng1;

            case "com.yongbaojiankang.dangmaladoctor":
                return R.drawable.bow_doctor_zheng1;
        }
        return R.drawable.bow_zheng1;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    @Override
    protected void refreshingImpl() {
        animationDrawable.start();
    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {
        mHeaderImage.setVisibility(VISIBLE);
        mHeaderImage.clearAnimation();
    }
}

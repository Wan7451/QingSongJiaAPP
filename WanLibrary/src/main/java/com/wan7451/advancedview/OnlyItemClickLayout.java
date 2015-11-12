package com.wan7451.advancedview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2015/8/19.
 */
public class OnlyItemClickLayout extends LinearLayout {
    public OnlyItemClickLayout(Context context) {
        super(context);
    }

    public OnlyItemClickLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

}

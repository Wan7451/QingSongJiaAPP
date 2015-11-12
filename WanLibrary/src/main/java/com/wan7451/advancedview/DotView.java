package com.wan7451.advancedview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.wan7451.wanadapter.mylibrary.R;

public class DotView extends View {
    private static final int DEFAULT_DOT_COLOR = 0xFFF58579;
    private static final int DEFAULT_DASH_WIDTH = 1;
    private static final int DEFAULT_DASH_GAP = 3;
    private Paint p;
    private int width;
    private int height;
    private int dash;
    private int mBorderColor;
    private int mDashWidth;
    private int mDashGap;

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DotView);
        mBorderColor = a.getColor(R.styleable.DotView_dot_color, DEFAULT_DOT_COLOR);

        mDashWidth = a.getDimensionPixelOffset(R.styleable.DotView_dash_width, 0);
        mDashGap = a.getDimensionPixelOffset(R.styleable.DotView_dash_gap, 0);
        a.recycle();
        init(context);
    }

    public DotView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStyle(Style.FILL);
        p.setColor(mBorderColor);

        final float scale = context.getResources().getDisplayMetrics().density;
        if (mDashWidth == 0) {
            mDashWidth = (int) (DEFAULT_DASH_WIDTH * scale + 0.5);
        }
        if (mDashGap == 0) {
            mDashGap = (int) (DEFAULT_DASH_GAP * scale + 0.5);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (width > 10) {
            for (int i = 0; i < width; i += mDashGap) {
                canvas.drawLine(i, 0, i += mDashWidth, 0, p);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        p.setStrokeWidth(height);
        this.postInvalidate();
    }


}

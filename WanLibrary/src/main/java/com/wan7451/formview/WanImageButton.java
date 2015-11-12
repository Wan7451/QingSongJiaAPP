package com.wan7451.formview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by Administrator on 2015/8/17.
 */
public class WanImageButton extends ImageButton {
    private String textTypeface;
    private Paint paint;

    public WanImageButton(Context context) {
        super(context);
        setTextTypeface("fonts/myFont.ttf");
    }

    public WanImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextTypeface("fonts/myFont.ttf");
    }

    public WanImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTextTypeface("fonts/myFont.ttf");
    }

    private String _text = "";
    private int _color = 0;
    private float _textsize = 0f;


    public void setText(String text) {
        this._text = text;
    }

    public void setColor(int color) {
        this._color = color;
    }

    public void setTextSize(float textsize) {
        this._textsize = textsize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(_color);
        paint.setTextSize(_textsize);
        canvas.drawText(_text, canvas.getWidth() / 2, (canvas.getHeight() / 2) + 12, paint);
    }

    public void setTextTypeface(String path) {
        //将字体文件保存在assets/fonts/目录下，创建Typeface对象
        Typeface typeFace = TypefaceUtils.getTypeface(getContext(), path);
        //使用字体
        paint.setTypeface(typeFace);

        invalidate();
    }
}

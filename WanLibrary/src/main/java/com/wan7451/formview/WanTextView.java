package com.wan7451.formview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/8/17.
 */
public class WanTextView extends TextView {
    public WanTextView(Context context) {
        super(context);
//        setTextTypeface("fonts/myFont.ttf");
    }

    public WanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setTextTypeface("fonts/myFont.ttf");
    }

    public WanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setTextTypeface("fonts/myFont.ttf");
    }

    /**
     * 设置字体
     *
     * @param path assets/fonts/目录下字体的路径  "fonts/HandmadeTypewriter.ttf"
     */
    public void setTextTypeface(String path) {
        //将字体文件保存在assets/fonts/目录下，创建Typeface对象
        Typeface typeFace = TypefaceUtils.getTypeface(getContext(), path);
        //使用字体
        setTypeface(typeFace);
    }

}

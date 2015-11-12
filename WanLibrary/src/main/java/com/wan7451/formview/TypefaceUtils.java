package com.wan7451.formview;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Administrator on 2015/8/17.
 */
public class TypefaceUtils {

    private static String p = "";
    private static Typeface typeFace;

    public static Typeface getTypeface(Context context, String path) {
        if (typeFace == null || !p.equals(path)) {
            p = path;
            typeFace = Typeface.createFromAsset(context.getAssets(), path);
        }
        return typeFace;
    }
}

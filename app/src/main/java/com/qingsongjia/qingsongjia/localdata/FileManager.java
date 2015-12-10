package com.qingsongjia.qingsongjia.localdata;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by wanggang on 15/12/10.
 */
public class FileManager {

    public static File getCacheFile(Context context) {
        File root = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            root = context.getExternalCacheDir();
        } else {
            root = context.getCacheDir();
        }
        return root;
    }


    public static File getCacheImageFile(Context context) {
        return new File(getCacheFile(context), "img");
    }


    public static File getTopImage(Context context){
        return new File(getCacheImageFile(context),"topImgs.jpg");
    }
}

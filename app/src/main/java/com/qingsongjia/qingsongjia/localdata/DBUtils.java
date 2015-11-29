package com.qingsongjia.qingsongjia.localdata;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qingsongjia.qingsongjia.bean.KeMu;
import com.qingsongjia.qingsongjia.bean.TiKu;

import java.io.File;

/**
 * Created by wanggang on 15/11/29.
 */
public class DBUtils {


    public static File getDBFile(Context context) {
        File root = context.getFilesDir();
        //判断文件夹是否存在，不存在就新建一个
        if (!root.exists()) {
            root.mkdirs();
        }
        File dbf = new File(root, "qsj_exam.db");
        return dbf;
    }

    public static SQLiteDatabase getDBManager(Context context) {
        return SQLiteDatabase.openOrCreateDatabase(getDBFile(context), null);
    }

    /**
     * 获得当前的题目
     */
    public static Cursor getCurrentTiMu(Context context, int type, int position) {
        SQLiteDatabase dbManager = getDBManager(context);
        KeMu kemu = LocalPreference.getCurrentKemu(context);

        Cursor c = dbManager.query("web_note",
                null,
                "kemu=? and id=?",
                new String[]{kemu.getIndex() + "", position + ""},
                null,
                null,
                null);
        return c;
    }

    public static void saveOrUpdateCurrentId(Context context, int id) {
        SQLiteDatabase dbManager = getDBManager(context);
        KeMu kemu = LocalPreference.getCurrentKemu(context);
        TiKu tiKu = LocalPreference.getCurrentTiKu(context);
        dbManager.query("test_do",
                null,
                "kemu_type=? and car_type=? and test_id=?",
                new String[]{kemu.getIndex()+"",tiKu.getIndex()+"",id+""},
                null, null, null);

    }

    /**
     * 获取当前做的题目进度
     */
    public static int getCurrentTiMu(Context context) {
        SQLiteDatabase dbManager = getDBManager(context);
        KeMu kemu = LocalPreference.getCurrentKemu(context);
        TiKu tiKu = LocalPreference.getCurrentTiKu(context);
        Cursor c = dbManager.query(
                "test_do",
                new String[]{"test_id"},
                "kemu_type=? and car_type=?",
                new String[]{kemu.getIndex() + "", tiKu.getIndex() + ""},
                null,
                null,
                "test_id desc");
        if (c.getCount() > 0) {
            c.moveToNext();
            return c.getInt(c.getColumnIndex("test_id"));
        }
        return 1;
    }

}

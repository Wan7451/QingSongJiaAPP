package com.qingsongjia.qingsongjia.localdata;

import android.content.ContentValues;
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
    public static Cursor getCurrentTiMu(Context context, int position) {
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

    /**
     * 保存做过的题目
     *
     * @param id
     * @param my_answer
     * @param true_answer
     */
    public static void saveOrUpdateCurrentId(Context context,
                                             int id,
                                             String my_answer,
                                             String true_answer) {
        SQLiteDatabase dbManager = getDBManager(context);
        KeMu kemu = LocalPreference.getCurrentKemu(context);
        TiKu tiKu = LocalPreference.getCurrentTiKu(context);
        Cursor c = dbManager.query("test_do",
                null,
                "kemu_type=? and car_type=? and test_id=?",
                new String[]{kemu.getIndex() + "", tiKu.getIndex() + "", id + ""},
                null, null, null);
        ContentValues values = new ContentValues();


        if (c.getCount() > 0) {
            c.moveToNext();
            //已经存在，更新
            values.put("update_time", System.currentTimeMillis());
            if (!my_answer.equals(true_answer)) {
                values.put("wrong_count", c.getInt(c.getColumnIndex("wrong_count") + 1));
            }
            dbManager.update("test_do",
                    values,
                    "kemu_type=? and car_type=? and test_id=?",
                    new String[]{kemu.getIndex() + "", tiKu.getIndex() + "", id + ""});
        } else {
            //不存在，插入
            values.put("my_answer", my_answer);
            values.put("true_answer", true_answer);

            values.put("is_show_in_wrong", false); //是否显示错误的答案
            values.put("is_correct", false);  //是否收藏
            values.put("car_type", tiKu.getIndex());
            values.put("kemu_type", kemu.getIndex());

            values.put("add_time", System.currentTimeMillis());
            if (my_answer.equals(true_answer)) {
                values.put("wrong_count", 0);
            } else {
                values.put("wrong_count", 1);
            }
            values.put("test_id", id);
            dbManager.insert("test_do", null, values);
        }


    }


    /**
     * 获取当前做的题目进度
     */
    public static int getLastTiMu(Context context) {
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
            int test_id = c.getInt(c.getColumnIndex("test_id"));
            c.close();
            return test_id;
        }
        c.close();

        return 0;
    }

}

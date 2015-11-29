package com.qingsongjia.qingsongjia.localdata;

import android.app.IntentService;
import android.content.Intent;
import android.database.sqlite.SQLiteException;

import com.qingsongjia.qingsongjia.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 拷贝数据库文件到SD卡
 */
public class CopyDBFileService extends IntentService {
    public static final String ACTION_COPY_DBFILE = "copyFile";


    public CopyDBFileService() {
        super("CopyDBFileService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_COPY_DBFILE.equals(action)) {
                handleActionCopyDBFile();
            }
        }
    }

    public boolean copyDataBase() {
        try {

            File dbf = DBUtils.getDBFile(this);
            FileOutputStream os = null;
            os = new FileOutputStream(dbf);//得到数据库文件的写入流
            InputStream is = getResources().openRawResource(R.raw.jxedt_user_20151103);//得到数据库文件的数据流
            byte[] buffer = new byte[1024 * 2];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }
            os.flush();
            os.close();
            is.close();
            return true;//复制文件成功
        } catch (SQLiteException e) {
            return false;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void handleActionCopyDBFile() {
        copyDataBase();
    }

}

package com.qingsongjia.qingsongjia.localdata;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.qingsongjia.qingsongjia.utils.EventData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

public class DownloadFileService extends IntentService {

    public static final String ACTION_DOWN_IMAGE = "imgs";
    private static final int BUFFER_SIZE = 4096;

    public DownloadFileService() {
        super("DownloadFileService");
    }

    public static void startDownImages(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadFileService.class);
        intent.setAction(ACTION_DOWN_IMAGE);
        intent.putExtra("imgPath", param1);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWN_IMAGE.equals(action)) {
                String path = intent.getStringExtra("imgPath");
                downImage(path);
            }
        }
    }

    private void downImage(String path) {
        File f = FileManager.getTopImage(this);
        try {
            saveToFile(path, f);
            EventBus.getDefault().post(new EventData(EventData.TYPE_CHANGETOP, f));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将HTTP资源另存为文件
     *
     * @param destUrl
     * @param file
     * @throws Exception
     */
    public void saveToFile(String destUrl, File file) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;

        //建立链接  
        url = new URL(destUrl);
        httpUrl = (HttpURLConnection) url.openConnection();
        //连接指定的资源  
        httpUrl.connect();
        //获取网络输入流  
        bis = new BufferedInputStream(httpUrl.getInputStream());
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }

        fos = new FileOutputStream(file);

        //保存文件  
        while ((size = bis.read(buf)) != -1)
            fos.write(buf, 0, size);

        fos.close();
        bis.close();
        httpUrl.disconnect();
    }

}

package com.qingsongjia.qingsongjia.activity;

import android.content.Intent;

import com.qingsongjia.qingsongjia.localdata.CopyDBFileService;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.wan7451.base.WanApplication;

/**
 * Created by wanggang on 15/11/14.
 */
public class App extends WanApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if(LocalPreference.isFirstUse(this)){
            Intent i=new Intent();
            i.setAction(CopyDBFileService.ACTION_COPY_DBFILE);
            i.setClass(this, CopyDBFileService.class);
            startService(i);
        }
    }
}

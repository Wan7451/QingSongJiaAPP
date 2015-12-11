package com.qingsongjia.qingsongjia.activity;

import android.content.Intent;

import com.qingsongjia.qingsongjia.bean.CityData;
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


    private CityData currCity; //当前过滤的城市
    private CityData currArea; //当前过滤的地区

    public CityData getCurrCity() {
        return currCity;
    }

    public void setCurrCity(CityData currCity) {
        this.currCity = currCity;
    }

    public CityData getCurrArea() {
        return currArea;
    }

    public void setCurrArea(CityData currArea) {
        this.currArea = currArea;
    }
}

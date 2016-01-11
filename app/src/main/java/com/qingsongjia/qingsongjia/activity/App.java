package com.qingsongjia.qingsongjia.activity;

import android.content.Intent;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.CityData;
import com.qingsongjia.qingsongjia.localdata.CopyDBFileService;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.umeng.analytics.MobclickAgent;
import com.wan7451.base.WanApplication;

import cn.jpush.android.api.JPushInterface;

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


        MobclickAgent.setDebugMode( true );

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }


    public DisplayImageOptions getDisplayOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .displayer(new FadeInBitmapDisplayer(300))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        return options;
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


    private String exchangeDri_type;

    public String getExchangeDri_type() {
        return exchangeDri_type;
    }

    public void setExchangeDri_type(String exchangeDri_type) {
        this.exchangeDri_type = exchangeDri_type;
    }
}

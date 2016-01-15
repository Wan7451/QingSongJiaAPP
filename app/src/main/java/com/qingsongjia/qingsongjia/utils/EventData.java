package com.qingsongjia.qingsongjia.utils;

import java.util.Objects;

/**
 * Created by wanggang on 15/12/5.
 */
public class EventData {

    /**
     * 约练数据
     */
    public static final int TYPE_YUELIAN = 0;

    /**
     * 更新广告条
     */
    public static final int TYPE_CHANGETOP = 1;

    /**
     * 刷新个人信息
     */
    public static final int TYPE_REFRESH_MENU = 2;

    /**
     * 刷新交流数据
     */
    public static final int TYPE_REFRESH_EXCHANGE = 3;

    /**
     * 刷新驾校数据
     */
    public static final int TYPE_REFRESH_SCHOOL = 4;


    /**
     * 刷新练习列表状态
     */
    public static final int TYPE_REFRESH_LIANXI = 5;

    /**
     * 刷新陪驾列表状态
     */
    public static final int TYPE_REFRESH_PEIJIA = 6;

    private int type;
    private Object data;

    public EventData(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

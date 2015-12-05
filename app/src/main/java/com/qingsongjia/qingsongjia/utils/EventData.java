package com.qingsongjia.qingsongjia.utils;

import java.util.Objects;

/**
 * Created by wanggang on 15/12/5.
 */
public class EventData {

    /**
     * 约练数据
     */
    public static final int TYPE_YUELIAN=0;

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

package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/11/29.
 */
public enum KeMu {
    KEMU1("kemu1",1),
    KEMU2("kemu2",2),
    KEMU3("kemu3",3),
    KEMU4("kemu4",4);
    private final String value;
    private final int index;

    KeMu(String value,int index) {
        this.value = value;
        this.index=index;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}

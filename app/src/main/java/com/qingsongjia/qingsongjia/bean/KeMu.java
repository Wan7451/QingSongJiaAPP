package com.qingsongjia.qingsongjia.bean;

/**
 * Created by wanggang on 15/11/29.
 */
public enum KeMu {
    KEMU1("kemu1","科目一",1),
    KEMU2("kemu2","科目二",2),
    KEMU3("kemu3","科目三",3),
    KEMU4("kemu4","科目四",4);
    private final String value;
    private final int index;
    private final String name;

    KeMu(String value,String name,int index) {
        this.value = value;
        this.index=index;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
